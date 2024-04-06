
public class SongStruct {
	private String songname;
	private String songadd;
	private SongStruct next;
	private SongStruct prev;

	public SongStruct(String songname,String songadd) {
		this.songname = songname;
		this.songadd = songadd;
		this.next = null;
		this.prev = null;
	}

	public String getsongname() {
		return songname;
	}

	public void setsongname(String songname) {
		this.songname = songname;
	}

	public SongStruct getNext() {
		return next;
	}

	public void setNext(SongStruct next) {
		this.next = next;
	}

	public SongStruct getPrev() {
		return prev;
	}

	public void setPrev(SongStruct prev) {
		this.prev = prev;
	}

	public String getsongadd() {
		return songadd;
	}

	public void setsongadd(String songadd) {
		this.songadd = songadd;
	}

}
