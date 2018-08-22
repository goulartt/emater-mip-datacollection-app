package br.edu.utfpr.cp.emater.mip.view.survey;

import br.edu.utfpr.cp.emater.mip.domain.field.field.Field;
import br.edu.utfpr.cp.emater.mip.domain.field.field.FieldRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.harvest.HarvestRepository;
import br.edu.utfpr.cp.emater.mip.domain.survey.surveyfield.SurveyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum SurveyFieldLabels {
    PAGE_TITLE("Unidades de Referência Participantes da Pesquisa"),
    SELECT_FIELD_PAGE_TITLE("Selecionar Unidades de Referência para Pesquisa"),
//    FIELD_FORM_PAGE_TITLE("Dados da Unidade de Referência para Pesquisa"),
    ENTITY("Unidade de Referência"),
    ARTICLE("a"),
    URL_CREATE("/survey-field/create"),
    URL_UPDATE("/survey-field/update"),
    URL_DELETE("/survey-field/delete");

    private String value;

    SurveyFieldLabels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum SurveyFieldPath {
    SUCCESS_CREATE("redirect:/survey-field"),
    SUCCESS_UPDATE("redirect:/survey-field"),
    SUCCESS_DELETE("redirect:/survey-field"),
    SUCCESS_READ("redirect:/survey-field"),
    TEMPLATE_PATH("/survey/survey-field/index"),
    TEMPLATE_SELECT_FIELD_PATH("/survey/survey-field/select-field"),
    TEMPLATE_SURVEY_FIELD_FORM_PATH("/survey/survey-field/field-form");

    private String value;

    SurveyFieldPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

@Controller
@RequestMapping ("/survey-field")
public class SurveyFieldController {
    
    private final SurveyFieldRepository surveyFieldRepository;
    private final FieldRepository fieldRepository;
    private final HarvestRepository harvestRepository;

    @Autowired
    public SurveyFieldController(SurveyFieldRepository surveyFieldRepository, FieldRepository fieldRepository, HarvestRepository harvestRepository) {
        this.surveyFieldRepository = surveyFieldRepository;
        this.fieldRepository = fieldRepository;
        this.harvestRepository = harvestRepository;
    }
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String findAll (Model data) {
        data.addAttribute("surveyFields", surveyFieldRepository.findAll());

        data.addAttribute("pageTitle", SurveyFieldLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", SurveyFieldLabels.ARTICLE.getValue());
        data.addAttribute("entity", SurveyFieldLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", SurveyFieldLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", SurveyFieldLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", SurveyFieldLabels.URL_DELETE.getValue());

        return SurveyFieldPath.TEMPLATE_PATH.getValue();
    }
    
    @RequestMapping (value = "/select-field", method = RequestMethod.GET)
    public String selectFieldForSurvey (Model data) {
        data.addAttribute("fields", fieldRepository.findAll());
        data.addAttribute("pageTitle", SurveyFieldLabels.SELECT_FIELD_PAGE_TITLE.getValue());
                
        return SurveyFieldPath.TEMPLATE_SELECT_FIELD_PATH.getValue();
    }
    
    @RequestMapping (value = "/field-form", method = RequestMethod.GET)
    public String surveyFieldForm (@RequestParam int fieldId, Model data) {
        data.addAttribute("harvests", harvestRepository.findAll());
        
        Field selectedField = fieldRepository.findById(new Long(fieldId)).get();
        
        data.addAttribute("selectedField", selectedField);
                
        data.addAttribute("pageTitle", String.format("Dados da UR '%s' (%s, %s)", selectedField.getName(), selectedField.getFarmer().getName(), selectedField.getCity().getName()));
        
        return SurveyFieldPath.TEMPLATE_SURVEY_FIELD_FORM_PATH.getValue();
    }
    
}
