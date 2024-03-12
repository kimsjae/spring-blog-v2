package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BoardNativeRepository.class)
public class BoardNativeRepositoryTest {

    @Autowired // IoC에 있는 것을 D.I 해줌
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void updateById_test() {
        // given
        String title = "제목수정1";
        String content = "내용수정1";
        String username = "bori";
        Integer id = 1;

        // when
        boardNativeRepository.updateById(title, content, username, id);

        // then
        Board board = boardNativeRepository.findById(id);
        System.out.println("updateById_test/board : " + board);
        assertThat(board.getTitle()).isEqualTo("제목수정1");
        assertThat(board.getContent()).isEqualTo("내용수정1");
        assertThat(board.getUsername()).isEqualTo("bori");
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardNativeRepository.deleteById(id);

        // then
        List<Board> boardList = boardNativeRepository.findAll();
        assertThat(boardList.size()).isEqualTo(3);
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardNativeRepository.findById(id);
        System.out.println("findById_test : " + board);

        // then
        // org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardNativeRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

}
