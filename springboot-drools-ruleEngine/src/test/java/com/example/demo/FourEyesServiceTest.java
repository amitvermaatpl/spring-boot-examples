package com.example.demo;

import com.example.demo.domain.FourEyes;
import com.example.demo.repo.FourEyesRepository;
import com.example.demo.services.FourEyesService;
import com.querydsl.core.BooleanBuilder;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;




import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
		@Ignore
public class FourEyesServiceTest {

	@Autowired
	private FourEyesRepository fourEyesRepository;


	@Autowired
	private FourEyesService fourEyesService;


	@Test
	public void a1PopulatesDatabase(){
		fourEyesRepository.save(FourEyes.builder().name("Test1").build());
		fourEyesRepository.save(FourEyes.builder().name("Test2").build());
		assertThat(fourEyesRepository.findAll()).size().isEqualTo(2);

	}

	@Test
	public void a2UpdateShouldAddOneForApproval() {

		fourEyesService.updateEntity(FourEyes.builder().id(1l).name("Updated").build());
		Page<FourEyes> toApprove = fourEyesService.getEntriesForApproval(new BooleanBuilder(),PageRequest.of(0,10) );
		assertThat(toApprove.getContent()).size().isEqualTo(1);
	}

	@Test
	public void a3RejectShouldDeleteUpdated() throws Exception {
		Page<FourEyes> toApprove = fourEyesService.getEntriesForApproval(new BooleanBuilder(),PageRequest.of(0,10) );
		fourEyesService.reject(toApprove.getContent().iterator().next().getId());
		assertThat(fourEyesRepository.findAll()).size().isEqualTo(2);
	}

	@Test
	public void a4ApproveShouldUpdate(){
		long id =fourEyesService.updateEntity(FourEyes.builder().id(1l).name("Updated").build()).get().getId();
		fourEyesService.approve(id);
		assertThat(fourEyesService.getEntries(new BooleanBuilder(),PageRequest.of(0,10) ).getContent()).size().isEqualTo(2);

	}
}
