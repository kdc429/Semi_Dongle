package common;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile=null;
		do {
			long currentTime=System.currentTimeMillis();//현재시간
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HHmmssSSS");
			int rndNum=(int)(Math.random()*10000);
			String oldName=oldFile.getName();//현재 사용자가 올린 파일명
			String ext="";
			int dot=oldName.lastIndexOf(".");//확장자를 잘라내기 위한 값
			if(dot>-1) {//lastIndexOf:못 찾으면 -1 출력~
				ext=oldName.substring(dot);//확장자만 따로 분리
			}
			//파일 rename
			String newName="newsfeed"+sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			newFile= new File(oldFile.getParent(),newName);
			//oldFile.getParent(): 부모 디렉토리 경로
			//newName: 새로 만들어진 파일 이름
		}while(!createNewFile(newFile));
		
		
		return newFile;
	}
	
	public boolean createNewFile(File f) {
		
		try {
			//파일이 있으면 false 발생 파일이 없으면 생성!
			return f.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
