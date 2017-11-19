/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pl.prazuch.wojciech.City;
import pl.prazuch.wojciech.Country;
import pl.prazuch.wojciech.CountryDeleteBeanRemote;
import pl.prazuch.wojciech.CountryInsertBeanRemote;
import pl.prazuch.wojciech.CountrySelectBeanRemote;
import pl.prazuch.wojciech.CountryUpdateBeanRemote;
import pl.prazuch.wojciech.CityDeleteBeanRemote;
import pl.prazuch.wojciech.CityUpdateBeanRemote;
import pl.prazuch.wojciech.CitySelectBeanRemote;
import pl.prazuch.wojciech.CityInsertBeanRemote;


/**
 *
 * @author wojciechprazuch
 */

public class CRUDMenu {
    

    
    /**
     * a flag to check whether user does not want to communicate with the database anymore
     */
    private boolean isUserDoneWithDatabase;




    public boolean isUserDoneWithDatabase() {
        return isUserDoneWithDatabase;
    }


    /**
     * constructor of the CRUDMenu view class
     * @param em EntityManager for communication with the database
     */
    public CRUDMenu() throws NamingException {
        
        this.isUserDoneWithDatabase = false;
        displayCRUDChoices();

        
    }
    
    /**
     * displays which CRUD operation to perform and then calls an appropriate method for that operation
     */
    private void displayCRUDChoices() throws NamingException{
        int choice = 0;
        while(choice <1 || choice > 5)
        {
        System.out.println("Choose one of the CRUD operations:");
        System.out.println("1. Create");
        System.out.println("2. Retrieve");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        
        Scanner scan = new Scanner(System.in);
        try{
            choice = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choice = 1;
        }
        
        }
        switch (choice) {
            case 1:  displayCreateChoices();
                     break;
            case 2:  displayRetrieveChoices();
                     break;
            case 3:  displayUpdateChoices();
                     break;
            case 4:  displayDeleteChoices();
                     break;
            case 5:  this.isUserDoneWithDatabase = true;
                     break;
        }
    }
    
    /**
     * Displays a menu, how we can search for entities in the table
     */
    private void displayRetrieveChoices() throws NamingException{
        
        int choiceofFinding = 0;
        
        while(choiceofFinding <1 || choiceofFinding > 3)
        {
        System.out.println("You may find records by");
        System.out.println("1. ID");
        System.out.println("2. displaying all records");
        System.out.println("3. by one of class fields");
        Scanner scan = new Scanner(System.in);
        try{
            choiceofFinding = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choiceofFinding = 1;
        }
        
        }
        
        int choiceofEntity = displayEntities();
        
        switch(choiceofFinding){
            case 1:  findRecordByID(choiceofEntity);
                     break;
            case 2:  findRecordByDisplayingAllRecords(choiceofEntity);
                     break;
            case 3:  findRecordByOneOfAttributes(choiceofEntity);
                     break;
        }
        
        
    }
    
    /**
     * Displays which entities can we choose from
     * @return a choice of entity, 1 if country, 2 if city
     */
    private int displayEntities(){
        
        int choice = 0;
        
        while(choice <1 || choice > 2)
        {
        System.out.println("You may influence entity:");
        System.out.println("1. Country");
        System.out.println("2. City");
        Scanner scan = new Scanner(System.in);
        try{
            choice = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choice = 1;
        }
        
        }
        return choice;
        
    }
    
    /**
     * diplays text asking to input an ID of the searched entity
     * @param entity 1 if Country, 2 if City
     */
    private void findRecordByID(int entity) throws NamingException
    {
        int id;
        System.out.println("Enter ID");
        Scanner scan = new Scanner(System.in);
        try{
            id = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            id = 1;
        }
        
        if(entity == 1){
            
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            System.out.println(countrysbr.selectCountryByID(id));
            
        }
        else
        {
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            System.out.println(citysbr.selectCityByID(id));
        }
    }

    /**
     * displays which entities we can create
     */
    private void displayCreateChoices() throws NamingException {
        
        int choiceOfEntity = displayEntities();
        
        switch(choiceOfEntity){
            case 1:  createCountryRecord();
                     break;
            case 2:  try{
                        createCityRecord();
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Bad Date Format!");
                    }
                     break;
        }
        
        
    }

    /**
     * displays choices of updating the entities
     */
    private void displayUpdateChoices() throws NamingException {
        int choiceofFinding = 0;
        
        while(choiceofFinding <1 || choiceofFinding > 3)
        {
        System.out.println("You may update records by");
        System.out.println("1. ID");
        System.out.println("2. changing all records");
        System.out.println("3. by one of class fields");
        Scanner scan = new Scanner(System.in);
        choiceofFinding = scan.nextInt();
        
        }
        
        int choiceofEntity = displayEntities();
        
        switch(choiceofFinding){
            case 1:
                try {
                    updateRecordByID(choiceofEntity);
                } catch (Exception ex) {

                }
                break;
            case 2:  updateRecordByDisplayingAllRecords(choiceofEntity);
                     break;
            case 3:  updateRecordByOneOfAttributes(choiceofEntity);
                     break;
        }
    }

    /**
     * displays the choices for deleting the entities
     */
    private void displayDeleteChoices() throws NamingException {
        int choiceofFinding = 0;
        
        while(choiceofFinding <1 || choiceofFinding > 3)
        {
        System.out.println("You may delete records by");
        System.out.println("1. ID");
        System.out.println("2. deleting all records");
        System.out.println("3. by one of class fields");
        Scanner scan = new Scanner(System.in);
        try{
            choiceofFinding = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choiceofFinding = 1;
        }
        
        }
        
        int choiceofEntity = displayEntities();
        
        
        switch(choiceofFinding){
            case 1:  deleteRecordByID(choiceofEntity);
                     break;
            case 2:  deleteAllRecords(choiceofEntity);
                     break;
            case 3:  deleteRecordByOneOfAttributes(choiceofEntity);
                     break;
        }
    }

    /**
     * a method for choosing which table's all the records should be displayed
     * @param choiceOfEntity 1, if Country, 2 if City
     */
    private void findRecordByDisplayingAllRecords(int choiceOfEntity) throws NamingException {
        
        if(choiceOfEntity == 1){
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            System.out.println(countrysbr.selectAllCountries());

        }
        else
        {
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            System.out.println(citysbr.selectAllCities());
        }
        
    }

    /**
     * a method for choosing which table's all the records with a given attribute's value should be displayed
     * @param choiceOfEntity 1, if Country, 2 if City
     */
    private void findRecordByOneOfAttributes(int choiceOfEntity) throws NamingException {
        
        
        if(choiceOfEntity == 1){
        
            String attribute = displayMenuForFindingCountryByAttribute();

            String comparator = displayComparators();
            String expression = getExpressionToCompareWith();
            
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            System.out.println(countrysbr.selectAllCountriesWithCriteria(attribute, comparator, expression));

            
        }
        else{
            
            String attribute = displayMenuForFindingCityByAttribute();
            
            String comparator = displayComparators();
            String expression = getExpressionToCompareWith();
            
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            System.out.println(citysbr.selectAllCitiesWithCriteria(attribute, comparator, expression));
            
        }
            

            
    }

    /**
     * displays which attribute to search by
     * @return a name of the attribute in string
     */
    private String displayMenuForFindingCountryByAttribute() {
        int choice = 0;
        while (choice < 1 || choice > 4) {
            System.out.println("You may find a country by:");
            System.out.println("1. name");
            System.out.println("2. continent");
            System.out.println("3. language");
            System.out.println("4. population size");
            Scanner scan = new Scanner(System.in);
            try{
            choice = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choice = 1;
        }
            
            
        }
        String attribute;
        switch(choice){
            case 1:  attribute = "name";
            break;
            case 2:  attribute = "continent";
            break;
            case 3:  attribute = "language";
            break;
            case 4:  attribute = "populationSize";
            break;
            default: attribute = "name";
            break;
            
        }
        return attribute;
    }

    /**
     * displays which attribute to search by
     * @return a name of the attribute in string
     */
    private String displayMenuForFindingCityByAttribute() {
        int choice = 0;
        while (choice < 1 || choice > 4) {
            System.out.println("You may find a city by:");
            System.out.println("1. name");
            System.out.println("2. population size");
            System.out.println("3. postal code");
            System.out.println("4. founding date");
            Scanner scan = new Scanner(System.in);
            try{
            choice = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Choosing case 1");
            choice = 1;
        }
        }
        
        String attribute;
        
        switch (choice) {
            case 1:
                attribute = "name";
                break;
            case 2:
                attribute = "populationSize";
                break;
            case 3:
                attribute = "postalCode";
                break;
            case 4:
                attribute = "foundingDate";
                break;
            default:
                attribute = "name";
                break;
        }
        
        return attribute;
        
    }
        
    /**
     * displays possible comparators for SQL
     * @return a chosen comparator in string
     */
    private String displayComparators(){
        
        int choice = 0;
        
        while (choice < 1 || choice > 2) {
                System.out.println("You may compare by");
                System.out.println("1. like");
                System.out.println("2. =");
                Scanner scan = new Scanner(System.in);
                try{
                    choice = scan.nextInt();

                }
                catch(InputMismatchException e)
                {
                    System.out.println("Incorrect input !");
                    System.out.println("Choosing case 1");
                    choice = 1;
                }

            }
        
        switch(choice){
            case 1:  return "=";
                     
            default:  return "like";
            
           
        }
        
        
    }

    /**
     * displays a Menu for supplying data for Country new record
     */
    private void createCountryRecord() throws NamingException {
        
        Scanner scan = new Scanner(System.in);
        
        Country country = new Country();
        System.out.println("Enter country name");
        country.setName(scan.nextLine());
        System.out.println("Enter country continent");
        country.setContinent(scan.nextLine());
        System.out.println("Enter country populationSize");
        int populationSize;
        try{
            populationSize = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Setting populationSize to 1");
            populationSize = 1;
        }
        country.setPopulationSize(populationSize);
        scan.nextLine();
        System.out.println("Enter country language");
        country.setLanguage(scan.nextLine());
        
        
        CountryInsertBeanRemote countryibr;
        InitialContext ctx = new InitialContext();
        countryibr = (CountryInsertBeanRemote) ctx.lookup("countryInsertBean1");
        countryibr.insertCountry(country);

    }

    /**
     * displays a Menu for supplying data for Country new record
     * @throws ParseException throws in case of bad Date Format (dd/MM/yyyy)
     */
    private void createCityRecord() throws ParseException, NamingException {
        Scanner scan = new Scanner(System.in);
        
        City city = new City();
        System.out.println("Enter city name");
        city.setName(scan.nextLine());
        System.out.println("Enter city postal code");
        city.setPostalCode(scan.nextLine());
        System.out.println("Enter city population size");
        int populationSize;
        try{
            populationSize = scan.nextInt();

        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input !");
            System.out.println("Setting populationSize to 1");
            populationSize = 1;
        }
        city.setPopulationSize(populationSize);
        scan.nextLine();
        System.out.println("Enter city founding date (dd/MM/yyyy)");
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = scan.nextLine();
        Date date = fmt.parse(dateString);
        city.setFoundingDate(date);
        System.out.println("Enter country id");
        int id;
        try {
        id = scan.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input!");
            System.out.println("Setting id to 1");
            id = 1;
        }
        scan.nextLine();
        
        
        CitySelectBeanRemote citysbr;
        InitialContext ctx = new InitialContext();
        citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
        
        
        while(citysbr.selectCityByID(id) != null)
        {
            System.out.println("Enter correct country id");
            id = scan.nextInt();
            scan.nextLine();
        }
        
        CountrySelectBeanRemote countrysbr;
        countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");

        city.setCountry(countrysbr.selectCountryByID(id));
        
        
        CityInsertBeanRemote cityibr;
        cityibr = (CityInsertBeanRemote) ctx.lookup("cityInsertBean1");
        cityibr.insertCity(city);
        //CityQueryHelper cityqh = new CityQueryHelper(em);
        //cityqh.insertCountry(city);

    }

    /**
     * gets a value to compare the attributes with
     * @return a value in string
     */
    private String getExpressionToCompareWith() {
        System.out.println("Enter expression to compare with:");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    
    }

    /**
     * displays a menu for updating a record by id
     * @param choiceofEntity 1 if Country, 2 if City
     * @throws ParseException when bad Date format (dd/MM/yyyy)
     */
    private void updateRecordByID(int choiceofEntity) throws ParseException, NamingException {
        System.out.println("Enter ID for deleting the record:");
        Scanner scan = new Scanner(System.in);
        int id;
        try {
        id = scan.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input!");
            System.out.println("Setting id to 1");
            id = 1;
        }
        scan.nextLine();
        String attribute;
        String value;
        if(choiceofEntity == 1)
        {
            attribute = displayMenuForFindingCountryByAttribute();
            value = getValueToUpdateWith();
            //CountryQueryHelper countryqh = new CountryQueryHelper(em);
            //countryqh.updateCountryById(attribute, value, id);
            
            CountryUpdateBeanRemote countryubr;
            InitialContext ctx = new InitialContext();
            countryubr = (CountryUpdateBeanRemote) ctx.lookup("countryUpdateBean1");
            countryubr.updateCountryById(attribute, value, id);
            
            
        }
        else{
            attribute = displayMenuForFindingCityByAttribute();
            value = getValueToUpdateWith();
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            //cityqh.updateCountryById(attribute, value, id);
            
            CityUpdateBeanRemote cityubr;
            InitialContext ctx = new InitialContext();
            cityubr = (CityUpdateBeanRemote) ctx.lookup("cityUpdateBean1");
            cityubr.updateCityById(attribute, value, id);
            
        }
    }

    /**
     * displays a menu for updating all records
     * @param choiceofEntity 1 if Country, 2 if City
     */
    private void updateRecordByDisplayingAllRecords(int choiceofEntity) throws NamingException {
        String attribute;
        String value;
        if(choiceofEntity == 1)
        {
            attribute = displayMenuForFindingCountryByAttribute();
            value = getValueToUpdateWith();
            
            CountryUpdateBeanRemote countryubr;
            InitialContext ctx = new InitialContext();
            countryubr = (CountryUpdateBeanRemote) ctx.lookup("countryUpdateBean1");
            countryubr.updateCountryByAllRecords(attribute, value);
            
            
        }
        else{
            attribute = displayMenuForFindingCityByAttribute();
            value = getValueToUpdateWith();
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            //cityqh.updateCountryByAllRecords(attribute, value);
            
            CityUpdateBeanRemote cityubr;
            InitialContext ctx = new InitialContext();
            cityubr = (CityUpdateBeanRemote) ctx.lookup("cityUpdateBean1");
            cityubr.updateCityByAllRecords(attribute, value);
            
        }
    }

    /**
     * displays menu and supplies necessary data for update statement
     * @param choiceofEntity 1 if Country, 2 if City
     */
    private void updateRecordByOneOfAttributes(int choiceofEntity) throws NamingException {
        int choice = 0;
        String attribute;
        String value;
        String attributeToCompare;
        String comparator;
        String valueToCompare;
        if(choiceofEntity == 1)
        {
            attribute = displayMenuForFindingCountryByAttribute();
            value = getValueToUpdateWith();
            attributeToCompare = displayMenuForFindingCountryByAttribute();
            comparator = displayComparators();
            valueToCompare = getValueToUpdateWith();
            
            //CountryQueryHelper countryqh = new CountryQueryHelper(em);
            //countryqh.updateCountryByAttribute(attribute, value, attributeToCompare, comparator, valueToCompare);
            CountryUpdateBeanRemote countryubr;
            InitialContext ctx = new InitialContext();
            countryubr = (CountryUpdateBeanRemote) ctx.lookup("countryUpdateBean1");
            countryubr.updateCountryByAttribute(attribute, value, attributeToCompare, comparator, valueToCompare);
            
            
        }
        else{
            attribute = displayMenuForFindingCityByAttribute();
            value = getValueToUpdateWith();
            attributeToCompare = displayMenuForFindingCityByAttribute();
            comparator = displayComparators();
            valueToCompare = getValueToUpdateWith();
            
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            //cityqh.updateCityByAttribute(attribute, value, attributeToCompare, comparator, valueToCompare);
            CityUpdateBeanRemote cityubr;
            InitialContext ctx = new InitialContext();
            cityubr = (CityUpdateBeanRemote) ctx.lookup("cityUpdateBean1");
            cityubr.updateCityByAttribute(attribute, value, attributeToCompare, comparator, valueToCompare);
            
            
        }
    }

    /**
     * Displays a menu for deleting an Entity by id
     * @param choiceofEntity 1 if Country, 2 if City
     */
    private void deleteRecordByID(int choiceofEntity) throws NamingException {
        System.out.println("Enter ID for deleting the record:");
        Scanner scan = new Scanner(System.in);
        int id;
        try {
        id = scan.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Incorrect input!");
            System.out.println("Setting id to 1");
            id = 1;
        }
        scan.nextLine();
        if(choiceofEntity == 1)
        {
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            if(countrysbr.selectCountryByID(id) != null)
            {
                CountryDeleteBeanRemote countrydbr;
                countrydbr = (CountryDeleteBeanRemote) ctx.lookup("countryDeleteBean1");
                countrydbr.deleteRecordByID(id);
            }
        }
        else{
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            
            if(citysbr.selectCityByID(id) != null)//cityqh.selectCityByID(id) != null)
            {
                CityDeleteBeanRemote citydbr;
                citydbr = (CityDeleteBeanRemote) ctx.lookup("cityDeleteBean1");
                citydbr.deleteRecordByID(id);
            }
        }
    }

    private void deleteAllRecords(int choiceofEntity) throws NamingException {
        if(choiceofEntity == 1)
        {
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            if(countrysbr.selectAllCountries() != null)
            {
                CountryDeleteBeanRemote countrydbr;
                countrydbr = (CountryDeleteBeanRemote) ctx.lookup("countryDeleteBean1");
                countrydbr.deleteAllRecords();
            }
        }
        else{
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            
            if(citysbr.selectAllCities() != null)//cityqh.selectAllCities() != null)
            {
                CityDeleteBeanRemote citydbr;
                citydbr = (CityDeleteBeanRemote) ctx.lookup("cityDeleteBean1");
                citydbr.deleteAllRecords();
            }
        }
    }

    /**
     * displays a menu for deleting record by one of attributes and calls methods for supplying data
     * @param choiceOfEntity 1 if Country, 2 if City
     */
    private void deleteRecordByOneOfAttributes(int choiceOfEntity) throws NamingException {
        
        
        if(choiceOfEntity == 1){
        
            String attribute = displayMenuForFindingCountryByAttribute();

            String comparator = displayComparators();
            String expression = getExpressionToCompareWith();
            
            CountrySelectBeanRemote countrysbr;
            InitialContext ctx = new InitialContext();
            countrysbr = (CountrySelectBeanRemote) ctx.lookup("countrySelectBean1");
            if(countrysbr.selectAllCountriesWithCriteria(attribute, comparator, expression) != null)
            {
                CountryDeleteBeanRemote countrydbr;
                countrydbr = (CountryDeleteBeanRemote) ctx.lookup("countryDeleteBean1");
                countrydbr.deleteAllCountriesWithCriteria(attribute, comparator, expression);
            }
            
        }
        else{
            
            String attribute = displayMenuForFindingCityByAttribute();
            
            String comparator = displayComparators();
            String expression = getExpressionToCompareWith();
            
            //CityQueryHelper cityqh = new CityQueryHelper(em);
            CitySelectBeanRemote citysbr;
            InitialContext ctx = new InitialContext();
            citysbr = (CitySelectBeanRemote) ctx.lookup("citySelectBean1");
            
            
            if(citysbr.selectAllCitiesWithCriteria(attribute, comparator, expression) != null)//cityqh.selectAllCitiesWithCriteria(attribute, comparator, expression) != null)
            {
                CityDeleteBeanRemote citydbr;
                citydbr = (CityDeleteBeanRemote) ctx.lookup("cityDeleteBean1");
                citydbr.deleteAllCitiesWithCriteria(attribute, comparator, expression);
            }
            
        }
        
    }

    /**
     * gets a value for update
     * @return a value in string
     */
    private String getValueToUpdateWith() {
        System.out.println("Enter value to update");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    
    
    
}
