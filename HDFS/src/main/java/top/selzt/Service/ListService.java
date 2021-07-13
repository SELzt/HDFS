package top.selzt.Service;

import java.io.IOException;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import Common.common;
import top.selzt.model.FileModel;

@Service
public class ListService {
	public List<FileModel> ls(HttpServletRequest request){
		//JSONObject data = ToJson.request2Json(request);
		//String path = data.getString("path");
		String path = request.getParameter("path");
		List<FileModel> results = null;
		try {
			FileSystem fs = FileSystem.get(common.conf);//多线程不要随便close
			results = new LinkedList<>();
			FileModel fileModel;
			Path listf=new Path(path);
			  //4.创建FileStatus对象，调用listStatus方法
			FileStatus[] stats=fs.listStatus(listf);
			for(FileStatus f : stats) {
				fileModel = new FileModel();
				fileModel.setFileName(f.getPath().getName());
				if(f.isDirectory())
					fileModel.setFileType("1");//文件夹
				else {
					fileModel.setFileType("0");//文件
					//计算文件大小
					DecimalFormat df = new DecimalFormat("0.00");
					fileModel.setFileSize(df.format(fs.getContentSummary(f.getPath()).getSpaceConsumed()/1024.0)+"KB");
				}
				results.add(fileModel);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
}
