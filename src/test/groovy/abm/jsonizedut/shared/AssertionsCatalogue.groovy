package abm.jsonizedut.shared

import abm.jsonizedut.Customer

class AssertionsCatalogue {

    static def assertData(Customer actual) {
        actual.id == 123
        actual.name == "John Doe"
        actual.contact == "johndoe@test.com"
    }

}
