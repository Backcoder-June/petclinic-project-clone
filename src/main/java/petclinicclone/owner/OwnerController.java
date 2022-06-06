package petclinicclone.owner;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

public class OwnerController {

    //Service 를 아예 안만들고 Repository 를 가지고 하네 , 이 project 가 이런건지 통상 이런건지 더 찾아봐야겠다

    private final OwnerRepository owners;            //Owner 테이블 이름도 owners 인데. 연관이 있는건지
                                                     // 왜 ownerRepository 로 안하고 굳이

    public OwnerController(OwnerRepository clinicService) {              //여기도 왜 생성자 그대로 안하고 굳이 clinic 으로?
        this.owners = clinicService;
    }

    @InitBinder                                      //<보충>
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId){
        return ownerId == null ? new Owner() : this.owners.findById(ownerId);

    }

    @GetMapping("/owners/new")
    public String initCreationForm(Map<String, Object> model){
        Owner owner = new Owner();
        model.put("owner", owner);
        return "owners/createOrUpdateOwnerForm.html";
    }

    @PostMapping("/owners/new")
    public String processCreationform(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()) {
           return "owners/createOrUpdateOwnerForm.html";
        }
           else{ this.owners.save(owner);

               return "redirect:/owners/" + owner.getId();
        }
    }

// 지금 이해하기 어려운 부분이 많다. 단계 밟아서 다시 오자.












}
