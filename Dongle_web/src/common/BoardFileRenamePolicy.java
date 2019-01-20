package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BoardFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		System.out.println("파일들어옴");
		File newFile=null;
		do {
			long currentTime=System.currentTimeMillis();//현재시간
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum=(int)(Math.random()*10000);
			String oldName=oldFile.getName();//현재사용자가 올린 파일명
			String ext="";
			int dot=oldName.lastIndexOf(".");//확장자를 짤라내기 위한 값
			if(dot > -1)//lastIndexOf : 못찾으면 -1 출력~
			{
				ext=oldName.substring(dot);//확장자만 따로 분리
			}
			//파일 rename
			String newName=sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			//olfFile.getParent() : 부모디렉토리경로
			//newName : 새로만들어진 파일이름
			newFile=new File(oldFile.getParent(),newName);
		}while(!createNewFile(newFile));
				
		return newFile;
	}
	
	public boolean createNewFile(File f)
	{
		try {
			//파일이 있으면 false 파일이 없으면 true
			return f.createNewFile();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
