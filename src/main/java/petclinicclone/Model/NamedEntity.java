package petclinicclone.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BaseEntity{
// 아 이거 강아지이름!
    @Column(name =  "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString(){
        return this.getName();
    }
    // 직접 getName 값을 return 하게 사용하려고
    // getName -> return name   return getName 은 그럼 뭐지 차이점? <보충>



}
