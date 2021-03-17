package day0317;
// 게시글을 담을 Board 클래스

public class Board {
    
    // 1. 필드
    
    private int id;
    private String title;
    private String user;
    private String content;
    
    // 2. 메소드
    // 겟터/셋터
    // 겟터/셋터 쉽게 만드는 방법
    // 화면 빈칸 오른쪽 클릭 -> source -> generate Getters and Setters
    // -> 겟터/셋터가 필요한 필드 클릭 후 generate
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    // equals()
    
    public boolean equals(Object o) {
        if (o instanceof Board) {

            Board b = (Board) o;

            if (title.equals(b.title)) {
                return true;
            }
        }

        return false;
    }
    
    // 내용 출력 printBoard()
    public void printBoard() {
        
        System.out.println();
        System.out.println("=====================================");
        System.out.printf("글번호: %04d\n", id);
        System.out.printf("제목: %s\n", title);
        System.out.printf("작성자: %s\n", user);
        System.out.println("--------------------------------------");
        System.out.println("                내용                   ");
        System.out.println("---------------------------------------");
        System.out.println(content);
        System.out.println("=======================================");
        System.out.println();
    }

}
