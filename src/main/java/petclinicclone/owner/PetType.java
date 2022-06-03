package petclinicclone.owner;

import petclinicclone.Model.NamedEntity;

import javax.persistence.Table;

@Table(name = "types")
public class PetType extends NamedEntity {
}
// 아무것도 없는 PetType class 객체로 써먹고, Table 만들어서 JoinColumn 해서 써먹으려고