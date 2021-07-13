package top.selzt.Service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import Common.common;
@Service
public class FolderService {
	//创建文件夹
	public int createFolder(HttpServletRequest request) {
		int code = 20001;//20000正确 20001错误
		String folder = request.getParameter("NewFolder");
		Path path = new Path(folder);
		FileSystem fs;
		try {
			fs = FileSystem.get(common.conf);
			boolean flag = fs.mkdirs(path);
			if(flag) 
				code = 20000;
			else 
				code = 20001;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			code = 20001;
			e.printStackTrace();
			return code;
		}
		
		return code;
	}
	//递归删除文件夹
	public int delete(HttpServletRequest request) {
		int code = 20001;
		String data = request.getParameter("file");
		FileSystem fs;
		try {
			fs = FileSystem.get(common.conf);
			Path path = new Path(data);
			boolean flag =fs.delete(path, true);
			if(flag)
				code = 20000;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}
}
