import java.io.IOException;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Songs {
	private SongStruct head = null;
	private SongStruct tail = null;
	public static SongStruct current = null;

	void insertBegin(String songname, String songadd) {
		if (contains(songadd)) {
			System.out.println("Song is already present in list");
			return;
		}
		SongStruct node1 = new SongStruct(songname, songadd);
		if (head == null) {
			head = tail = node1;
			current = node1;
		} else {
			node1.setNext(head);
			head.setPrev(node1);
			head = node1;
		}
		tail.setNext(head);
		head.setPrev(tail);
		System.out.println("Song added successfullly");
	}

	void insertEnd(String songname, String songadd) {
		if (contains(songadd)) {
			System.out.println("Song is already present in list");
			return;
		}
		SongStruct node2 = new SongStruct(songname, songadd);
		if (head == null) {
			head = tail = node2;
			current = node2;
		} else {
			node2.setPrev(tail);
			tail.setNext(node2);
			tail = node2;
		}
		tail.setNext(head);
		head.setPrev(tail);
		System.out.println("Song added successfullly");
	}

	void insertPos(String songname, String songadd, int pos) {
		if (contains(songadd)) {
			System.out.println("Song is already present in list");
			return;
		}
		SongStruct node3 = new SongStruct(songname, songadd);
		if (head == null) {
			head = tail = node3;
			current = node3;
		}
		if (pos < 1) {
			System.out.println("position should be greater than or equal to 1");
		} else if (pos == 1) {
			node3.setNext(head);
			node3.setPrev(tail);
			head.setPrev(node3);
			tail.setNext(node3);
			head = node3;
			System.out.println("Song added successfullly");
		} else {
			SongStruct temp = head;
			int i;
			for (i = 1; i < pos - 1 && temp.getNext() != head; i++) {
				temp = temp.getNext();
			}
			if (i == pos - 1) {
				node3.setNext(temp.getNext());
				node3.setPrev(temp);
				temp.setNext(node3);
				if (node3.getNext() != head) {
					temp = node3;
					temp = temp.getNext();
					temp.setPrev(node3);
				}
				if (temp == tail) {
					tail = node3;
					head.setPrev(tail);
				}
				System.out.println("Song added successfullly");
			} else {
				i = i + 1;
				System.out.println("There is no song in previous i.e " + i + "th position");
				System.out.println("Please add song in " + i + "th position");
			}
		}
	}

	void removeBeg() {
		if (head == null) {
			System.out.println("No songs in the Playlist to remove!");
		} else if (head == head.getNext()) {
			head = null;
			System.out.println("Removed Successfullly");
		} else {
			head = head.getNext();
			head.setPrev(tail);
			tail.setNext(head);
			current = head;
			System.out.println("Removed Successfullly");
		}
	}

	void removeEnd() {
		if (head == null) {
			System.out.println("No songs in the Playlist to remove!");
		} else if (head == head.getNext()) {
			head = null;
			System.out.println("Removed Successfullly");
		} else {
			tail = tail.getPrev();
			tail.setNext(head);
			head.setPrev(tail);
			current = head;
			System.out.println("Removed Successfullly");
		}
	}

	boolean contains(String songadd) {
		if (head == null) {
			return false; // If list is empty, return false
		}

		SongStruct temp = head;
		do {
			if (temp.getsongadd().equals(songadd)) {
				return true; // If we find the songadd, return true
			}
			temp = temp.getNext();
		} while (temp != head); // Repeat the loop until we've circled back to the head

		// If we finish the loop without finding the songadd, return false
		return false;
	}

	static void menu() {
		System.out.println("(HOME)");
		System.out.println("[1] Add song to the playlist");
		System.out.println("[2] Remove song from the playlist");
		System.out.println("[3] View all songs in playlist");
		System.out.println("[4] Play");
	}

	void viewAllSongs() {
		SongStruct temp;
		temp = head;
		if (head == null) {
			System.out.println("Play List is Empty!");
			System.out.println("Add songs in Playlist!");
		} else {
			while (temp.getNext() != head) {
				System.out.println(temp.getsongname());
				temp = temp.getNext();
			}
			System.out.println(temp.getsongname());
		}

	}

	void nextSong() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		if (current == null || current.getNext() == head) {
			current = head;
		} else {
			current = current.getNext();
		}
		// System.out.println(current.getsongname()+"");
	}

	void prevSong() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		// If current node is null or current node is head, move current to tail
		if (current == null || current == head) {
			current = tail;
		} else {
			// Move current to previous node
			current = current.getPrev();
		}

	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Songs song = new Songs();
		Scanner sc = new Scanner(System.in);
		boolean flag;
		System.out.println("---WELCOME TO PLAYLIST---");
		do {
			menu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				do {
					System.out.println("where you want to add your song");
					// System.out.print("[a] Append ");
					System.out.print("[a] Begin ");
					System.out.print("[b] End ");
					System.out.print("[c] At position ");
					char ch = sc.next().charAt(0);
					switch (ch) {

					case 'a':
						System.out.println("Add song name and song address:");
						String songname1 = sc.next();
						String songadd1 = sc.next();
						song.insertBegin(songname1, songadd1);
						break;
					case 'b':
						System.out.println("Add song name and song address:");
						String songname11 = sc.next();
						String songadd11 = sc.next();
						song.insertEnd(songname11, songadd11);
						break;
					case 'c':
						System.out.println("Add song name ,song address & pos you want:");
						String songname111 = sc.next();
						String songadd111 = sc.next();
						int pos = sc.nextInt();
						song.insertPos(songname111, songadd111, pos);
						break;
					default:
						System.out.println("Invalid Input");
					}
					System.out.println(" want to Add any ? (Yes|NO)");
					String Yeno = sc.next();
					if (Yeno.equalsIgnoreCase("yes"))
						flag = true;
					else
						flag = false;
				} while (flag);
				break;
			case 2:
				do {
					System.out.println("Which song you want to Remove");
					System.out.print("[a] First ");
					System.out.print("[b] Last ");
					char ch1 = sc.next().charAt(0);
					switch (ch1) {
					case 'a':
						song.removeBeg();
						break;
					case 'b':
						song.removeEnd();
						break;
					default:
						System.out.println("Invalid Input");
					}
					System.out.println("want to Remove any ? (Yes|NO)");
					String yesno = sc.next();
					if (yesno.equalsIgnoreCase("yes"))
						flag = true;
					else
						flag = false;
				} while (flag);

				break;
			case 3:
				song.viewAllSongs();
				break;
			case 4:
				AudioPlayer player = null;
				char op;
				if (current == null) {
					System.out.println("No current song to play.");
					System.out.println("Go to home and Add song ! ");
				} else {
					String songPath = current.getsongadd();
					try {
						player = new AudioPlayer(songPath);
						player.play();
					} catch (Exception e) {
						System.out.println("Error with playing sound.");
						e.printStackTrace();
					}
				}
				if (player != null) {
					do {
						System.out.print("[p]: pause ");
						System.out.print("[r]: resume ");
						// System.out.print("[k]: restart ");
						System.out.print("[o]: reset ");
						System.out.print("[n]: Next ");
						System.out.print("[l]: Prev ");
						System.out.print("[s]: stop ");
						op = sc.next().charAt(0);
						switch (op) {
						case 'p':
							player.pause();
							break;
						case 'r':
							player.resumeAudio();
							break;
						case 'o':
							player.resetAudioStream();
							System.out.println(Songs.current.getsongname() + " is playing");
							break;
						case 'n':
							song.nextSong();
							if (current != null) {
								String songPath = current.getsongadd();
								try {
									if (player != null && player.status.equals("play")) {
										player.stop();
									}
									player = new AudioPlayer(songPath);
									player.play();
								} catch (Exception e) {
									System.out.println("Error with playing sound.");
									e.printStackTrace();
								}
							}
							break;
						case 'l':
							song.prevSong();
							if (current != null) {
								String songPath = current.getsongadd();
								try {
									if (player != null && player.status.equals("play")) {
										player.stop();
									}
									player = new AudioPlayer(songPath);
									player.play();
								} catch (Exception e) {
									System.out.println("Error with playing sound.");
									e.printStackTrace();
								}
							}
							break;
						case 's':
							player.stop();
							break;
						default:
							System.out.println("select valid option!");
						}
					} while (op != 's');
				}
				break;
			default:
				System.out.println("select valid option!");
			}
			System.out.println("Go to Home ? (Yes|NO)");
			String yesno = sc.next();
			if (yesno.equalsIgnoreCase("yes"))
				flag = true;
			else
				flag = false;
		} while (flag);
	}

}
