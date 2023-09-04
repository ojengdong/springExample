package kr.kdata.upload.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileVO {
	private String uuid;
	private String fileName;
	private String contentType;
}
