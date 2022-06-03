package petclinicclone.owner;

import petclinicclone.Model.Person;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;


@Table(name = "owners")                                //Owner 전용 DB 테이블 생성. Table 이름 : owners 로 만듬
public class Owner extends Person {       //base 상속한 Person(fist,last name) 상속

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
    }

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









    //
}
