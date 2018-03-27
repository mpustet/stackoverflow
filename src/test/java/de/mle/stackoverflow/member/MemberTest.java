package de.mle.stackoverflow.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemberTest {
	@InjectMocks
	private Member member;
	@Mock
	private MemberDao memberDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createId() throws Exception {
		MembersIdDto dto = new MembersIdDto();
		dto.setId("967405286");
		when(memberDao.findNext()).thenReturn(dto);

		assertThat(member.createNewId()).isEqualTo("967405286");
	}
}