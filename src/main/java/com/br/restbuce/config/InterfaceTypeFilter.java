package com.br.restbuce.config;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class InterfaceTypeFilter extends AssignableTypeFilter {

	/**
	 * Creates a new {@link InterfaceTypeFilter}.
	 * 
	 * @param targetType
	 */
	public InterfaceTypeFilter(Class<?> targetType) {
		super(targetType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter#match(org.springframework.core.type.classreading.MetadataReader, org.springframework.core.type.classreading.MetadataReaderFactory)
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

		return metadataReader.getClassMetadata().isInterface() && super.match(metadataReader, metadataReaderFactory);
	}
}