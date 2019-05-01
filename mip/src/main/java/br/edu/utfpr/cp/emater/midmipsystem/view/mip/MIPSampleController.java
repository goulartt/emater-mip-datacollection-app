package br.edu.utfpr.cp.emater.midmipsystem.view.mip;

import br.edu.utfpr.cp.emater.midmipsystem.entity.mip.MIPSample;
import br.edu.utfpr.cp.emater.midmipsystem.entity.mip.Pest;
import br.edu.utfpr.cp.emater.midmipsystem.entity.mip.PestSize;
import br.edu.utfpr.cp.emater.midmipsystem.entity.survey.Survey;
import br.edu.utfpr.cp.emater.midmipsystem.view.ICRUDController;
import br.edu.utfpr.cp.emater.midmipsystem.exception.AnyPersistenceException;
import br.edu.utfpr.cp.emater.midmipsystem.exception.EntityAlreadyExistsException;
import br.edu.utfpr.cp.emater.midmipsystem.exception.EntityInUseException;
import br.edu.utfpr.cp.emater.midmipsystem.exception.EntityNotFoundException;
import br.edu.utfpr.cp.emater.midmipsystem.service.mip.MIPSampleService;
import br.edu.utfpr.cp.emater.midmipsystem.service.mip.PestService;
import br.edu.utfpr.cp.emater.midmipsystem.service.survey.SurveyService;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component (value = "mipSampleController")
@RequestScope
public class MIPSampleController extends MIPSample implements ICRUDController<MIPSample> {

    private final MIPSampleService mipSampleService;
    
    @Autowired
    public MIPSampleController(MIPSampleService aMipSampleService) {
        this.mipSampleService = aMipSampleService;
    }

    @Override
    public List<MIPSample> readAll() {
        return mipSampleService.readAll();
    }
    
    public List<Survey> readAllSurveysThatHasSample() {
        return mipSampleService.readAllSurveysThatHasSample();
    }
    
//    public List<PestSize> readAllPestSizes() {
//        return Arrays.asList(PestSize.values());
//    }    
//
//    @Override
//    public String create() {
//        var newPest = Pest.builder().usualName(this.getUsualName()).scientificName(this.getScientificName()).pestSize(this.getPestSize()).build();
//
//        try {
//            pestService.create(newPest);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", String.format("Inseto Praga [%s] criado com sucesso!", this.getUsualName())));
//            return "index.xhtml";
//
//        } catch (EntityAlreadyExistsException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Já existe um inseto praga com esse nome e tamanho! Use um nome/tamanho diferente."));
//            return "create.xhtml";
//
//        } catch (AnyPersistenceException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na gravação dos dados!"));
//            return "index.xhtml";
//        }
//    }
//
//    @Override
//    public String prepareUpdate(Long anId) {
//
//        try {
//            Pest existentPest = pestService.readById(anId);
//            this.setId(existentPest.getId());
//            this.setUsualName(existentPest.getUsualName());
//            this.setScientificName(existentPest.getScientificName());
//            this.setPestSize(existentPest.getPestSize());
//            
//            return "update.xhtml";
//
//        } catch (EntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Inseto praga não pode ser alterado porque não foi encontrado na base de dados!"));
//            return "index.xhtml";
//        }
//    }
//
//    @Override
//    public String update() {
//        var updatedPest = Pest.builder().id(this.getId()).usualName(this.getUsualName()).scientificName(this.getScientificName()).pestSize(this.getPestSize()).build();
//
//        try {
//            pestService.update(updatedPest);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Inseto praga alterado"));
//            return "index.xhtml";
//
//        } catch (EntityAlreadyExistsException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Já existe um inseto praga com esse nome/tamanho! Use um nome/tamanho diferente."));
//            return "update.xhtml";
//
//        } catch (EntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Inseto praga não pode ser alterado porque não foi encontrado na base de dados!"));
//            return "update.xhtml";
//
//        } catch (AnyPersistenceException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na gravação dos dados!"));
//            return "index.xhtml";
//        }
//
//    }
//
//    @Override
//    public String prepareDelete(Long anId) {
//
//        try {
//            var existentPest = pestService.readById(anId);
//            
//            this.setId(existentPest.getId());
//            this.setUsualName(existentPest.getUsualName());
//            this.setPestSize(existentPest.getPestSize());
//
//            return "delete.xhtml";
//
//        } catch (EntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Inseto praga não pode ser excluído porque não foi encontrado na base de dados!"));
//            return "index.xhtml";
//        }
//    }
//
//    @Override
//    public String delete() {
//        
//        try {
//            pestService.delete(this.getId());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Inseto praga excluído!"));
//            return "index.xhtml";
//
//        } catch (EntityNotFoundException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Inseto praga não pode ser excluído porque não foi encontrado na base de dados!"));
//            return "index.xhtml";
//            
//        } catch (EntityInUseException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Inseto praga não pode ser excluído porque está sendo usado em uma amostra!"));
//            return "index.xhtml";
//            
//        } catch (AnyPersistenceException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na gravação dos dados!"));
//            return "index.xhtml";
//        }
//    }

    @Override
    public String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate(Long anId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareDelete(Long anId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
