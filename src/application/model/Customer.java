package application.model;

public class Customer {
    private String name;
    private Company company;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company as this employees company, if they aren't connected
     *
     * @param company
     */
    public void setCompany(Company company) {
        if (this.company != company) {
            Company oldCompany = this.company;
            if (oldCompany != null) {
                oldCompany.removeCustomer(this);
            }
            this.company = company;
            if (company != null)
                company.addCustomer(this);
        }
    }
}
