package com.example.hrtool.web;

import com.example.hrtool.service.JobCandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobCandidateController.class)
class ApiExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobCandidateService candidateService;

	@Test
	void invalidRequestReturnsBadRequestWithValidationErrorBody() throws Exception {
		mockMvc.perform(post("/api/candidates")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "fullName": "",
					  "dateOfBirth": "1990-01-01",
					  "contactNumber": "123",
					  "email": "jane@example.com"
					}
					"""))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.error").value("Validation failed"));
	}

	@Test
	void illegalArgumentFromServiceReturnsBadRequestWithErrorBody() throws Exception {
		doThrow(new IllegalArgumentException("Candidate data invalid"))
				.when(candidateService)
				.updateCandidate(eq(1L), any(), anyList());

		mockMvc.perform(put("/api/candidates/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "fullName": "Jane Doe",
					  "dateOfBirth": "1990-01-01",
					  "contactNumber": "123456",
					  "email": "jane@example.com",
					  "skills": ["Java"]
					}
					"""))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.error").value("Candidate data invalid"));
	}
}
