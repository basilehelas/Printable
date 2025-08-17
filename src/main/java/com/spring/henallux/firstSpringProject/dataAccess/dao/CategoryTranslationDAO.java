package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.repository.TranslationRepository;
import com.spring.henallux.firstSpringProject.model.CategoryLabel;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(Transactional.TxType.SUPPORTS)
public class CategoryTranslationDAO implements CategoryTranslationDataAccess {

    private final TranslationRepository translations;

    public CategoryTranslationDAO(TranslationRepository translations) {
        this.translations = translations;
    }

    @Override
    public List<CategoryLabel> listCategoriesWithNames() {
        String languageId = LocaleContextHolder.getLocale().getLanguage(); // recup le cookie Locale de spring
        return translations.findByIdLanguageId(languageId).stream()
                .map(t -> new CategoryLabel(t.getId().getCategoryId(), t.getName()))
                .collect(Collectors.toList());
    }
}
