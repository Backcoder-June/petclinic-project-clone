package petclinicclone.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BaseEntity{

    @Column(name =  "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // + toString  overriding 해서 return this.getName(); 으로 써먹으려 한다.
    // 언제 필요해서 쓰는지 보자



}
