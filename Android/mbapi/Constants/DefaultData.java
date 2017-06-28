package mbapi.Constants;

import java.util.ArrayList;

import mbapi.Models.GenderOption;
import mbapi.Models.Relationship;

/**
 * Contains hard-coded data which is default for the studio.
 * This is to reduce the traffic for studios that relies on the system data.
 *
 * NOTES: DO NOT USE THIS CLASS IF:
 * YOU HAVE CUSTOM GENDERS OR MODIFIED SYSTEM GENDER ACTIVE STATUS.
 * YOU HAVE CUSTOM RELATIONSHIPS.
 * USE GetGenders AND GetRelationships UNDER SiteService INSTEAD.
 *
 * Created on 8/26/16.
 */
public class DefaultData
{
    private static ArrayList<Relationship> _relationships;
    private static ArrayList<GenderOption> _genderOptions;

    public static ArrayList<Relationship> getSystemRelationships()
    {
        if (_relationships != null)
        {
            return _relationships;
        }

        Relationship rel1 = new Relationship();
        rel1.ID = -8;
        rel1.RelationshipName1 = "Spouse";
        rel1.RelationshipName2 = "Spouse";

        Relationship rel2 = new Relationship();
        rel2.ID = -7;
        rel2.RelationshipName1 = "Sibling";
        rel2.RelationshipName2 = "Sibling";

        Relationship rel3 = new Relationship();
        rel3.ID = -6;
        rel3.RelationshipName1 = "Parent/Guardian";
        rel3.RelationshipName2 = "Child";

        Relationship rel4 = new Relationship();
        rel4.ID = -5;
        rel4.RelationshipName1 = "Receives emails for";
        rel4.RelationshipName2 = "Receives emails for";

        Relationship rel5 = new Relationship();
        rel5.ID = -4;
        rel5.RelationshipName1 = "Is Paid For By";
        rel5.RelationshipName2 = "Pays For";

        Relationship rel6 = new Relationship();
        rel6.ID = -3;
        rel6.RelationshipName1 = "Shares Membership";
        rel6.RelationshipName2 = "Shares Membership";

        Relationship rel7 = new Relationship();
        rel7.ID = -2;
        rel7.RelationshipName1 = "Shares pricing option";
        rel7.RelationshipName2 = "Shares pricing option";

        Relationship rel8 = new Relationship();
        rel8.ID = -1;
        rel8.RelationshipName1 = "Referred";
        rel8.RelationshipName2 = "Referred By";

        Relationship rel9 = new Relationship();
        rel9.ID = 2;
        rel9.RelationshipName1 = "Friend";
        rel9.RelationshipName2 = "Friend";

        Relationship rel10 = new Relationship();
        rel10.ID = 4;
        rel10.RelationshipName1 = "Company";
        rel10.RelationshipName2 = "Employee";

        Relationship rel11 = new Relationship();
        rel11.ID = 6;
        rel11.RelationshipName1 = "Partner";
        rel11.RelationshipName2 = "Partner";

        Relationship rel12 = new Relationship();
        rel12.ID = 7;
        rel12.RelationshipName1 = "Family";
        rel12.RelationshipName2 = "Family";

        _relationships = new ArrayList<>();
        _relationships.add(rel1);
        _relationships.add(rel2);
        _relationships.add(rel3);
        _relationships.add(rel4);
        _relationships.add(rel5);
        _relationships.add(rel6);
        _relationships.add(rel7);
        _relationships.add(rel8);
        _relationships.add(rel9);
        _relationships.add(rel10);
        _relationships.add(rel11);
        _relationships.add(rel12);

        return _relationships;
    }

    public static ArrayList<GenderOption> getDefaultGenderOptions()
    {
        if (_genderOptions != null)
        {
            return _genderOptions;
        }

        GenderOption gender1 = new GenderOption();
        gender1.ID = 1;
        gender1.Name = "None";
        gender1.IsActive = true;
        gender1.IsSystem = true;

        GenderOption gender2 = new GenderOption();
        gender2.ID = 2;
        gender2.Name = "Male";
        gender2.IsActive = true;
        gender2.IsSystem = true;

        GenderOption gender3 = new GenderOption();
        gender3.ID = 3;
        gender3.Name = "Female";
        gender3.IsActive = true;
        gender3.IsSystem = true;

        _genderOptions = new ArrayList<>();
        _genderOptions.add(gender1);
        _genderOptions.add(gender2);
        _genderOptions.add(gender3);

        return _genderOptions;
    }
}
