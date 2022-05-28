package files;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Item {
	final long serialVersionUID = 1L;
	private String score;
	private String time;
	private String duration;

	public Item(String score, String time, String duration) {
		this.score = score;
		this.time = time;
		this.duration = duration;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
