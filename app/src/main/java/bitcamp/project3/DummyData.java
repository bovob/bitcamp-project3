package bitcamp.project3;

import static bitcamp.project3.vo.Book.setSeqNo;

import bitcamp.project3.vo.Book;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

//        BorrowCommand bc = new BorrowCommand( bookCommand.getBookList());
//        returnCommand = new ReturnCommand( bookCommand.getBookList(), bc.getBorrowList(), UserCommand.getCurrentUser());

    //더미생성
    public static List<Book> addDummyBook() {
        List<Book> booklist = new ArrayList<>();
        String[] categories = {"소설", "과학", "역사", "자기계발", "철학"};
                              //NOV     SCI     HIS     SEL         PHI
        final String NOV = categories[0];
        final String SCI = categories[1];
        final String HIS = categories[2];
        final String SEL = categories[3];
        final String PHI = categories[4];

        String[][] dummy = {

                {"부자의 언어", "존 소포릭", SEL, "istj"},


                {"열한계단", "채사장", PHI, "isfj"},

                {"신비롭지 않은 여자들", "임소연", NOV, "infj"},
                {"죽어 마땅한 사람들", "피터스 완슨", NOV, "infj"},

                {"우리는 왜 서로를 미워하는가", "에즈라 클라인",SEL, "intj"},
                {"마션","앤디 위어",SCI,"intj"},


                {"사람을 움직이는 처세술","데일 카네기",SEL,"istp"},


                {"누구나 철학자가 되는 밤","김한승",SEL,"isfp"},

                {"내가 틀릴 수도 있습니다.", "비욘 나티코 린데블라드", SEL, "infp" },
                {"타인의 해석","말콤 글래드 웰",SEL,"infp"},

                {"우리는 SF를 좋아해", "김보영", SEL, "intp"},
                {"프리다칼로","프리다칼로",HIS,"intp"},


                {"Demian","헤르만헤세", NOV, "estp"},


                {"포노사피엔스","최재봉",SCI, "esfp"},

                {"내가 예쁘다고?", "황인찬", NOV, "enfp"},
                {"세상에서 가장 짧은 교양수업","데이비드",SEL,"enfp"},

                {"언택트건 컨택트건 잘 팔리는 말솜씨", "강동섭", SEL, "entp"},
                {"파피용","베르나르 베르베르",NOV,"entp"},


                {"유대인의 생각훈련","심정섭",SEL,"estj"},


                {"행복한 이기주의자","웨인다이어",SEL,"esfj"},

                {"지구를 살리는 옷장", "박진영", SCI, "enfj"},
                {"나의 라임오렌지나무","바스콘셀로스",NOV,"enfj"},

                {"보도 섀퍼의 이기는 습관", "보도 섀퍼", SEL, "entj" },
                {"다정히도 불어오는 바람","윤동주",NOV,"entj"}

        };

        int i=0;
        for(String[] dum : dummy){
            Book book = new Book(dum);
            book.setNo(++i);
            booklist.add(book);
            setSeqNo(i);
        }

        return booklist;
    }
}
