/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test.org.springdoc.api.app171;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springdoc.core.Constants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import test.org.springdoc.api.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = Constants.SPRINGDOC_CACHE_DISABLED + "=false")
public class SpringDocApp171Test extends AbstractSpringDocTest {

	@OpenAPIDefinition(info = @Info(
			title = "test", version = "v0")
	)
	@SpringBootApplication
	static class SpringDocTestApp {
	}

	@Test
	@Override
	public void testApp() throws Exception {
		Locale.setDefault(Locale.US);
		testApp(Locale.US);
		testApp(Locale.FRANCE);
	}

	private void testApp(Locale locale) throws Exception {
		className = getClass().getSimpleName();
		String testNumber = className.replaceAll("[^0-9]", "");
		MvcResult mockMvcResult =
				mockMvc.perform(get(Constants.DEFAULT_API_DOCS_URL).locale(locale).header(HttpHeaders.ACCEPT_LANGUAGE, locale.toLanguageTag())).andExpect(status().isOk())
						.andExpect(jsonPath("$.openapi", is("3.0.1"))).andReturn();
		String result = mockMvcResult.getResponse().getContentAsString();
		String expected = getContent("results/app" + testNumber + "-" + locale.getLanguage() + ".json");
		assertEquals(expected, result, true);
	}
}