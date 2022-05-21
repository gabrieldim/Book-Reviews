package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import mk.ukim.finki.booksreviews.model.request.ReviewerRequest;
import mk.ukim.finki.booksreviews.repository.ReviewerRepository;
import mk.ukim.finki.booksreviews.service.impl.ReviewerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewerServiceImplTest {

    List<Reviewer> reviewerList = List.of(
            Reviewer.of("Nikola", "Stanojkovski", "stanojkovski@gmail.com", "nikolaS@123", "Favourite quote 1", "Some bio 1"),
            Reviewer.of("Gabriel", "Dimitrievski", "dimitrievski@gmail.com", "dimitR1ev123", "Favourite quote 2", "Some bio 2"),
            Reviewer.of("Jovana", "Kocevska", "kocevska@gmail.com", "kovecsk@1a", "Favourite quote 3", "Some bio 3"),
            Reviewer.of("Petar", "Bujukovski", "bujukovski@gmail.com", "buko@vsk123", "Favourite quote 4", "Some bio 4"),
            Reviewer.of("Merxhan", "Bajrami", "stanojkovski@gmail.com", "stan0s123", "Favourite quote 5", "Some bio 5")
    );

    @Mock
    private ReviewerRepository reviewerRepository;

    private ReviewerService reviewerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.reviewerService = Mockito.spy(new ReviewerServiceImpl(this.reviewerRepository));
    }

    @Test
    public void testFindAll() {
        when(reviewerRepository.findAll()).thenReturn(List.of()).thenReturn(reviewerList);

        assertThat(reviewerService.findAll()).isNotNull().isEmpty();
        assertThat(reviewerService.findAll()).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(reviewerList);
        verify(reviewerRepository, times(2)).findAll();
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<Reviewer> page = new PageImpl<>(reviewerList);
        when(reviewerRepository.findAll(pageable)).thenReturn(Page.empty()).thenReturn(page);

        assertThat(reviewerService.findAllPageable(pageable)).isNotNull().isEmpty();
        assertThat(reviewerService.findAllPageable(pageable)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(page);
        verify(reviewerRepository, times(2)).findAll(pageable);
    }

    @Test
    public void testFindAllByReview() {
        Long reviewId = 1L;
        List<Reviewer> filteredReviewers = List.of(reviewerList.get(0));
        when(reviewerRepository.findAllByReviews_Id(reviewId)).thenReturn(List.of()).thenReturn(filteredReviewers);

        assertThat(reviewerService.findAllByReview(reviewId)).isNotNull().isEmpty();
        assertThat(reviewerService.findAllByReview(reviewId)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(filteredReviewers);
        verify(reviewerRepository, times(2)).findAllByReviews_Id(reviewId);
    }

    @Test
    public void testFindById() {
        Long bookId = 1L;
        Reviewer filteredReviewer = reviewerList.get(0);
        when(reviewerRepository.findById(bookId)).thenReturn(Optional.empty()).thenReturn(Optional.of(filteredReviewer));

        assertThat(reviewerService.findById(bookId)).isNotNull().isNotPresent();
        assertThat(reviewerService.findById(bookId)).isNotNull().isPresent().isEqualTo(Optional.of(filteredReviewer));
        verify(reviewerRepository, times(2)).findById(bookId);
    }

    @Test
    public void testRegisterReviewer() {
        Reviewer registeredReviewer = reviewerList.get(0);
        when(reviewerRepository.save(Mockito.any(Reviewer.class))).thenReturn(registeredReviewer);
        when(reviewerRepository.findByEmail(anyString())).thenReturn(Optional.of(reviewerList.get(1))).thenReturn(Optional.empty());

        ReviewerRequest invalidEmailPasswordReviewerRequest = new ReviewerRequest(registeredReviewer.getFirstName(), registeredReviewer.getLastName(),
                "invalidemail", "invalidpassword", registeredReviewer.getFavoriteQuote(), registeredReviewer.getBioDescription());
        assertThat(reviewerService.registerReviewer(invalidEmailPasswordReviewerRequest)).isNotNull().isNotPresent();

        ReviewerRequest invalidEmailReviewerRequest = new ReviewerRequest(registeredReviewer.getFirstName(), registeredReviewer.getLastName(),
                reviewerList.get(1).getEmail(), registeredReviewer.getPassword(), registeredReviewer.getFavoriteQuote(), registeredReviewer.getBioDescription());
        assertThat(reviewerService.registerReviewer(invalidEmailReviewerRequest)).isNotNull().isNotPresent();

        ReviewerRequest validReviewerRequest = new ReviewerRequest(registeredReviewer.getFirstName(), registeredReviewer.getLastName(),
                registeredReviewer.getEmail(), registeredReviewer.getPassword(), registeredReviewer.getFavoriteQuote(), registeredReviewer.getBioDescription());
        assertThat(reviewerService.registerReviewer(validReviewerRequest)).isNotNull().isPresent().isEqualTo(Optional.of(registeredReviewer));

        verify(reviewerRepository, times(1)).findByEmail(registeredReviewer.getEmail());
        verify(reviewerRepository, times(1)).save(any(Reviewer.class));
    }

    @Test
    public void testLoginReviewer() {
        Reviewer registeredReviewer = reviewerList.get(0);
        when(reviewerRepository.findByEmailAndPassword(registeredReviewer.getEmail(), registeredReviewer.getPassword())).thenReturn(Optional.of(registeredReviewer));

        assertThat(reviewerService.loginReviewer(new ReviewerRequest(null, null,
                "invalidEmail", "invalidPassword",
                null, null))).isNotNull().isNotPresent();
        assertThat(reviewerService.loginReviewer(new ReviewerRequest(null, null,
                registeredReviewer.getEmail(), registeredReviewer.getPassword(),
                null, null)))
                .isNotNull().isPresent().isEqualTo(Optional.of(registeredReviewer));

        verify(reviewerRepository, times(1)).findByEmailAndPassword(registeredReviewer.getEmail(), registeredReviewer.getPassword());
    }
}
