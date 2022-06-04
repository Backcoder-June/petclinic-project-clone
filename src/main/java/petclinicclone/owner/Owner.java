package petclinicclone.owner;

import org.springframework.core.style.ToStringCreator;
import org.springframework.util.Assert;
import petclinicclone.Model.Person;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Table(name = "owners")                                //Owner 전용 DB 테이블 생성. Table 이름 : owners
public class Owner extends Person {       //base 상속한 Person(first,last name) 상속

    // first, lastName 은 Person class 에서 Column 에 넣어뒀다.
    // Person 상속했으니까, 자동으로 owners 테이블 안으로 Column 화 되서 들어가는걸로 이해

    @Column(name = "address")           //Owner DB 테이블 안에 address 행 추가
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;
    //int integer  - 허용 가능한 정수 자릿수, 10자리까지 가능
    //int fraction - 허용 가능한 소수점 이하 자릿수, 즉 전화번호에 소수 못쓰게 하려고

    public String getAddress() {
        return address;
    }      //return this.address 로 하는데, 똑같은것 아닌가?

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //Getter Setter


    // pet 관련 쭉. Owner 랑 연결
    // toString overriding
    // addvisit 메소드 만들기


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")                                           // foreign key 정하는 법 <보!충!>
    @OrderBy("name")       //Pet 이 상속하는 NamedEntity 의 name
    private List<Pet> pets = new ArrayList<>();         //Pet 하고 mapping 시키면 Pet 의 정보가 다 가져와지고 사용가능해지는걸로

    public List<Pet> getPets() {
        return pets;
    }
    public void addPet(Pet pet) {

        if (pet.isNew()){                // 중복방지 .isNew() 써줬다
        getPets().add(pet);}
    }


    // 여기까지 Owner 에 이름 주소 도시 번호 pet 까지 연결해서 table 에 넣은것

    //여기서부턴 getPet 메소드
    // 다형성 이용해서 사실상 findbyname, findbyid, New 에 대한 대처까지 해주는것


    public Pet getPet(String name, Boolean b){    // 여기 para 로 boolean 안줘도 되있는데 무슨차이?
        return getPet(name, false);           // 어떻게 쓰는거지 <보충>
    }

    public Pet getPet(Integer id) {
        for (Pet pet: getPets()) {
            if (!pet.isNew()){                 //  ! (not) 활용
                Integer compId = pet.getId();
                if (compId.equals(id)){
                    return pet;
                }
            }

        }
        return null;
    }

    public Pet getPet(String name, boolean ignoreNew){                   // New 대처같은데 어렵다 <보충>
        name = name.toLowerCase();
        for (Pet pet:getPets()) {
            if (!ignoreNew || !pet.isNew()){
                String compName = pet.getName();
                compName = compName == null ? "" : compName.toLowerCase();
                if (compName.equals(name)){return pet;}

            }

        }
        return null;
    }


    // toString  overriding 해서 다 뽑아내는걸로 쓰기
    // toStringCreator? <보충>

       @Override
       public String toString(){
        return new ToStringCreator(this).append("id", this.getId()).append("new", this.isNew())
                .append("lastName", this.getLastName()).append("firstName", this.getFirstName())
                .append("address", this.address).append("city", this.city).append("telephone", this.telephone)
                .toString(); }


    // 방문 기록 추가하기 메소드

    public Owner addVisit(Integer petId, Visit visit){

        //Exception 설정
        Assert.notNull(petId, "Pet id 는 있어야 합니다");
        Assert.notNull(visit, "Visit 란 이 공백일 수 없습니다.");

        Pet pet = getPet(petId);
        Assert.notNull(pet, "pet id 가 유효하지 않습니다");

        //이게 핵심
        pet.addVisits(visit);

        return this;           // this 무엇? <보충>
    }






    //
}
