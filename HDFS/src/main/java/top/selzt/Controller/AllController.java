package top.selzt.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.selzt.Service.DownloadService;
import top.selzt.Service.FolderService;
import top.selzt.Service.ListService;
import top.selzt.model.FileModel;

@RestController
public class AllController {

	@Autowired
	private ListService listService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private DownloadService downloadService;
	@RequestMapping("/ls")
	public List<FileModel> ls(HttpServletRequest request){
		return listService.ls(request);
	}
	@RequestMapping("/createFolder")
	public int createFolder(HttpServletRequest request) {
		return folderService.createFolder(request);
	}
	@RequestMapping("/delete")
	public int delete(HttpServletRequest request) {
		return folderService.delete(request);
	}
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response) {
		downloadService.download(request,response);
	}
}
