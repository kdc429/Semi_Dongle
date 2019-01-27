package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class EditFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile=null;
		do {
			long currentTime=System.currentTimeMillis();//현재시간 
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum=(int)(Math.random()*10000);
			String oldName=oldFile.getName(); //현재 사용자가 올린 원래 파일명칭 받아둠
			String ext=""; //확장자명만 받아오기위해 잘라내기 위한 값
			int dot=oldName.lastIndexOf(".");//확장자를 잘라내기위한 값
			if(dot>-1)//lasdIndexOf:못찾으면 -1출력
			{
				ext=oldName.substring(dot);//dot로 자름(확장자만 따로 저장)
			}
			String newName="edit_"+sdf.format(new Date(currentTime))+"_"+rndNum+ext; //저장될 새로운 명칭!
			newFile=new File(oldFile.getParent(),newName); //메모리공간을 확립함
			//oldFile.getParent():부모디렉토리경로
			//newName:새로 만들어진 파일이름
		}
		while(!createNewFile(newFile));
		
		return newFile;
	}
	public boolean createNewFile(File f)
	{
		//중복된 명칭이있는지 확인하기위해서
		try {
			//파일이 있으면 false , 파일이 없으면 true
			return f.createNewFile(); //실질적으로 파일을 만드는것
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
