package top.selzt.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import Common.common;
@Service
public class DownloadService {
	public void download(HttpServletRequest request,HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		String realName = fileName.substring(fileName.lastIndexOf("/") + 1);
		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileSystem fs = FileSystem.get(common.conf);
			Path path = new Path(fileName);
			response.setContentLength((int)fs.getContentSummary(path).getSpaceConsumed());
			InputStream input = fs.open(path);
			OutputStream output = response.getOutputStream();
			byte[] buffer = new byte[1024*1024];
			int len = 0;
			while((len = input.read(buffer))>0) {
				output.write(buffer, 0, len);
			}
			input.close();
			output.flush();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
