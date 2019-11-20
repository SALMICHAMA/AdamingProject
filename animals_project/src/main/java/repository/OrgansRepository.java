package repository;

import java.util.List;

public interface OrgansRepository <Organs> {

    void saveNewOrgan(Organs organs);
    void findOrganByName(String organName);
    void deleteOrgan(Organs organs);
    List<Organs> getAllOrgans(Organs organs);

}
