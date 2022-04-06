/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.springdoc.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.filters.OpenApiMethodFilter;

import org.springframework.util.CollectionUtils;

import static org.springdoc.core.utils.Constants.GROUP_NAME_NOT_NULL;

/**
 * The type Grouped open api.
 * @author bnasslahsen
 */
public class GroupedOpenApi {

	/**
	 * The Group.
	 */
	private final String group;

	/**
	 * The Open api customisers.
	 */
	private final List<OpenApiCustomizer> openApiCustomizers;

	/**
	 * The Operation customizers.
	 */
	private final List<OperationCustomizer> operationCustomizers;

	/**
	 * The Paths to match.
	 */
	private final List<String> pathsToMatch;

	/**
	 * The Packages to scan.
	 */
	private final List<String> packagesToScan;

	/**
	 * The Packages to exclude.
	 */
	private final List<String> packagesToExclude;

	/**
	 * The Paths to exclude.
	 */
	private final List<String> pathsToExclude;

	/**
	 * The Produces to match.
	 */
	private final List<String> producesToMatch;

	/**
	 * The Headers to match.
	 */
	private final List<String> headersToMatch;

	/**
	 * The Consumes to match.
	 */
	private final List<String> consumesToMatch;

	/**
	 * The method filters to use.
	 */
	private final List<OpenApiMethodFilter> openApiMethodFilters;

	/**
	 * The Display name.
	 */
	private final String displayName;

	/**
	 * Instantiates a new Grouped open api.
	 *
	 * @param builder the builder
	 */
	private GroupedOpenApi(Builder builder) {
		this.group = Objects.requireNonNull(builder.group, GROUP_NAME_NOT_NULL);
		this.pathsToMatch = builder.pathsToMatch;
		this.packagesToScan = builder.packagesToScan;
		this.producesToMatch = builder.producesToMatch;
		this.consumesToMatch = builder.consumesToMatch;
		this.headersToMatch = builder.headersToMatch;
		this.packagesToExclude = builder.packagesToExclude;
		this.pathsToExclude = builder.pathsToExclude;
		this.displayName = StringUtils.defaultIfEmpty(builder.displayName, this.group);
		this.openApiCustomizers = Objects.requireNonNull(builder.openApiCustomizers);
		this.operationCustomizers = Objects.requireNonNull(builder.operationCustomizers);
		this.openApiMethodFilters = Objects.requireNonNull(builder.methodFilters);
		if (CollectionUtils.isEmpty(this.pathsToMatch)
				&& CollectionUtils.isEmpty(this.packagesToScan)
				&& CollectionUtils.isEmpty(this.producesToMatch)
				&& CollectionUtils.isEmpty(this.consumesToMatch)
				&& CollectionUtils.isEmpty(this.headersToMatch)
				&& CollectionUtils.isEmpty(this.pathsToExclude)
				&& CollectionUtils.isEmpty(this.packagesToExclude)
				&& CollectionUtils.isEmpty(openApiCustomizers)
				&& CollectionUtils.isEmpty(operationCustomizers)
				&& CollectionUtils.isEmpty(openApiMethodFilters))
			throw new IllegalStateException("Packages to scan or paths to filter or openApiCustomizers/operationCustomizers can not be all null for the group:" + this.group);
	}

	/**
	 * Builder builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Gets produces to match.
	 *
	 * @return the produces to match
	 */
	public List<String> getProducesToMatch() {
		return producesToMatch;
	}

	/**
	 * Gets headers to match.
	 *
	 * @return the headers to match
	 */
	public List<String> getHeadersToMatch() {
		return headersToMatch;
	}

	/**
	 * Gets consumes to match.
	 *
	 * @return the consumes to match
	 */
	public List<String> getConsumesToMatch() {
		return consumesToMatch;
	}

	/**
	 * Gets group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Gets paths to match.
	 *
	 * @return the paths to match
	 */
	public List<String> getPathsToMatch() {
		return pathsToMatch;
	}

	/**
	 * Gets packages to scan.
	 *
	 * @return the packages to scan
	 */
	public List<String> getPackagesToScan() {
		return packagesToScan;
	}

	/**
	 * Gets packages to exclude.
	 *
	 * @return the packages to exclude
	 */
	public List<String> getPackagesToExclude() {
		return packagesToExclude;
	}

	/**
	 * Gets paths to exclude.
	 *
	 * @return the paths to exclude
	 */
	public List<String> getPathsToExclude() {
		return pathsToExclude;
	}

	/**
	 * Gets open api customisers.
	 *
	 * @return the open api customisers
	 */
	public List<OpenApiCustomizer> getOpenApiCustomizers() {
		return openApiCustomizers;
	}

	/**
	 * Gets operation customizers.
	 *
	 * @return the operation customizers
	 */
	public List<OperationCustomizer> getOperationCustomizers() {
		return operationCustomizers;
	}

	/**
	 * Gets open api method filters.
	 *
	 * @return the open api method filters
	 */
	public List<OpenApiMethodFilter> getOpenApiMethodFilters() {
		return openApiMethodFilters;
	}

	/**
	 * Gets display name.
	 *
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * The type Builder.
	 * @author bnasslahsen
	 */
	public static class Builder {
		/**
		 * The Open api customisers.
		 */
		private final List<OpenApiCustomizer> openApiCustomizers = new ArrayList<>();

		/**
		 * The Operation customizers.
		 */
		private final List<OperationCustomizer> operationCustomizers = new ArrayList<>();

		/**
		 * The methods filters to apply.
		 */
		private final List<OpenApiMethodFilter> methodFilters = new ArrayList<>();

		/**
		 * The Group.
		 */
		private String group;

		/**
		 * The Paths to match.
		 */
		private List<String> pathsToMatch;

		/**
		 * The Packages to scan.
		 */
		private List<String> packagesToScan;

		/**
		 * The Packages to exclude.
		 */
		private List<String> packagesToExclude;

		/**
		 * The Paths to exclude.
		 */
		private List<String> pathsToExclude;

		/**
		 * The Produces to match.
		 */
		private List<String> producesToMatch;

		/**
		 * The Headers to match.
		 */
		private List<String> headersToMatch;

		/**
		 * The Consumes to match.
		 */
		private List<String> consumesToMatch;

		/**
		 * The Display name.
		 */
		private String displayName;

		/**
		 * Instantiates a new Builder.
		 */
		private Builder() {
			// use static factory method in parent class
		}

		/**
		 * Group builder.
		 *
		 * @param group the group
		 * @return the builder
		 */
		public Builder group(String group) {
			this.group = group;
			return this;
		}

		/**
		 * Paths to match builder.
		 *
		 * @param pathsToMatch the paths to match
		 * @return the builder
		 */
		public Builder pathsToMatch(String... pathsToMatch) {
			this.pathsToMatch = Arrays.asList(pathsToMatch);
			return this;
		}

		/**
		 * Packages to scan builder.
		 *
		 * @param packagesToScan the packages to scan
		 * @return the builder
		 */
		public Builder packagesToScan(String... packagesToScan) {
			this.packagesToScan = Arrays.asList(packagesToScan);
			return this;
		}

		/**
		 * Produces to match builder.
		 *
		 * @param producesToMatch the produces to match
		 * @return the builder
		 */
		public Builder producesToMatch(String... producesToMatch) {
			this.producesToMatch = Arrays.asList(producesToMatch);
			return this;
		}

		/**
		 * Consumes to match builder.
		 *
		 * @param consumesToMatch the consumes to match
		 * @return the builder
		 */
		public Builder consumesToMatch(String... consumesToMatch) {
			this.consumesToMatch = Arrays.asList(consumesToMatch);
			return this;
		}

		/**
		 * Headers to match builder.
		 *
		 * @param headersToMatch the headers to match
		 * @return the builder
		 */
		public Builder headersToMatch(String... headersToMatch) {
			this.headersToMatch = Arrays.asList(headersToMatch);
			return this;
		}

		/**
		 * Paths to exclude builder.
		 *
		 * @param pathsToExclude the paths to exclude
		 * @return the builder
		 */
		public Builder pathsToExclude(String... pathsToExclude) {
			this.pathsToExclude = Arrays.asList(pathsToExclude);
			return this;
		}

		/**
		 * Packages to exclude builder.
		 *
		 * @param packagesToExclude the packages to exclude
		 * @return the builder
		 */
		public Builder packagesToExclude(String... packagesToExclude) {
			this.packagesToExclude = Arrays.asList(packagesToExclude);
			return this;
		}

		/**
		 * Add open api customiser builder.
		 *
		 * @param openApiCustomizer the open api customiser
		 * @return the builder
		 */
		public Builder addOpenApiCustomizer(OpenApiCustomizer openApiCustomizer) {
			this.openApiCustomizers.add(openApiCustomizer);
			return this;
		}

		/**
		 * Add operation customizer builder.
		 *
		 * @param operationCustomizer the operation customizer
		 * @return the builder
		 */
		public Builder addOperationCustomizer(OperationCustomizer operationCustomizer) {
			this.operationCustomizers.add(operationCustomizer);
			return this;
		}

		/**
		 * Add method filter.
		 *
		 * @param methodFilter an additional filter to apply to the matched methods
		 * @return the builder
		 */
		public Builder addOpenApiMethodFilter(OpenApiMethodFilter methodFilter) {
			this.methodFilters.add(methodFilter);
			return this;
		}

		/**
		 * Display name builder.
		 *
		 * @param displayName the display name
		 * @return the builder
		 */
		public Builder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		/**
		 * Build grouped open api.
		 *
		 * @return the grouped open api
		 */
		public GroupedOpenApi build() {
			return new GroupedOpenApi(this);
		}
	}


	/**
	 * Add open api customizer.
	 *
	 * @param openApiCustomizer the open api customiser
	 */
	public void addOpenApiCustomizer(OpenApiCustomizer openApiCustomizer) {
		this.openApiCustomizers.add(openApiCustomizer);
	}
}
