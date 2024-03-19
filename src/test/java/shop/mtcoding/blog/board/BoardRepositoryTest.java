package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

@Import(BoardJPARepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Test
    public void randomquery_test(){
        int[] ids = {1,2,3};

        // select u from User u where u.id in (?,?,?);
        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
        System.out.println(q);
    }

//    @Test
//    public void findAllV2_test() {
//        String q1 = "select b from Board b order by b.id desc";
//        List<Board> boardList = boardRepository.findAll();
//
//
//        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
//        String q2 = "select u from User u where u.id in (:id1, :id2, :id3)"; // in쿼리
//        int user1 = 1;
//        int user2 = 2;
//        int user3 = 3;
//        q2 = q2.replace(":id1", user3+"").replace(":id2", user2+"").replace(":id3", user1+"");
//        System.out.println(q2);
//    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardJPARepository.findAll();
        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds) {
            System.out.println(i);
        }
        // select * from user_tb where id in (3, 2, 1);
    }

    @Test
    public void findAll_lazyloading_test() {
        List<Board> boardList = boardJPARepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });
    }

    @Test
    public void findAll_test() {
        // given


        // when
        boardJPARepository.findAll();

        // then
    }

    @Test
    public void findById_test() {
        int id = 1;

        boardJPARepository.findById(1);
    }
}
