package domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private int read_count;
	private String image_file;
	
	// insert, update
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	// list
	public BoardVO(int bno, String title, String writer, String reg_date, int read_count) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.reg_date = reg_date;
		this.read_count = read_count;
	}
	
	// detail
	public BoardVO(int bno, String title, String writer, String content, String reg_date, int read_count, String image_file) {
		this(bno,title,writer,reg_date,read_count);
		this.content = content;
		this.image_file = image_file;
	}
	
	// modify 작성자 확인용
	public BoardVO(int bno, String writer) {
		this.bno = bno;
		this.writer = writer;
	}
	
	// modify
	public BoardVO(int bno, String title, String writer, String content) {
		this(title,writer,content);
		this.bno = bno;
	}

	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", content=" + content + ", image_file=" + image_file + ", read_count="
				+ read_count + ", reg_date=" + reg_date + ", title=" + title + ", writer=" + writer + "]";
	}

	
}
