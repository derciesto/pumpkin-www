CREATE TABLE company_profile (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registration_type VARCHAR(100),
    gstin VARCHAR(15) UNIQUE,
    pan_no VARCHAR(10) UNIQUE,
    industry VARCHAR(100),
    sector VARCHAR(100),
    msme_registration_number VARCHAR(50) UNIQUE,
    incorporation_date DATE,
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    zip VARCHAR(20),
    country VARCHAR(100),
    inserted_or_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.inserted_or_updated_date = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER set_timestamp
BEFORE UPDATE ON company_profile
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();



-- Create company_profile table
CREATE TABLE public.company_profile (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registration_type VARCHAR(128),
    gstin VARCHAR(32),
    pan_no VARCHAR(32),
    industry VARCHAR(128),
    sector VARCHAR(128),
    msme_registration_number VARCHAR(64),
    incorporation_date DATE,
    address TEXT,
    city VARCHAR(128),
    state VARCHAR(128),
    zip VARCHAR(16),
    country VARCHAR(128),
    inserted_or_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.credit_requirement (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    load_format VARCHAR(128),
    requirement_description TEXT,
    end_use_of_fund TEXT,
    date_of_fund_requirement DATE,
    loan_amount int,
    loan_duration INT,
    collateral_type VARCHAR(128),
    purchase_date DATE,
    owned_by VARCHAR(128),
    purchase_value int,
    market_value int,
    description TEXT,
    FOREIGN KEY (company_id) REFERENCES public.company_profile(id) ON DELETE CASCADE
);

CREATE TABLE company_promoter (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    aadhar_number VARCHAR(12) UNIQUE NOT NULL,
    designation VARCHAR(100),
    shareholding INTEGER,
    age INTEGER NOT NULL,
    pan VARCHAR(10) UNIQUE NOT NULL,
    address TEXT,
    years_in_address INTEGER,
    din VARCHAR(20),
    FOREIGN KEY (company_id) REFERENCES company_profile (id) ON DELETE CASCADE
);

CREATE TABLE social_reference (
    id BIGSERIAL PRIMARY KEY,
    promoter_id BIGINT NOT NULL,
    social_network_name VARCHAR(100) NOT NULL,
    url TEXT NOT NULL,
    FOREIGN KEY (promoter_id) REFERENCES company_promoter (id) ON DELETE CASCADE
);

CREATE TABLE executive_association (
    id BIGSERIAL PRIMARY KEY,
    promoter_id BIGINT NOT NULL,
    association_name VARCHAR(100) NOT NULL,
    membership_number VARCHAR(50) UNIQUE NOT NULL,
    date_of_joining DATE NOT NULL,
    FOREIGN KEY (promoter_id) REFERENCES company_promoter (id) ON DELETE CASCADE
);

CREATE TABLE existing_loans (
    id bigserial PRIMARY KEY,
    company_id BIGINT NOT NULL,
    lending_institute VARCHAR(255) NOT NULL,
    loan_amount BIGINT NOT NULL,
    loan_type VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    emi BIGINT NOT NULL,
    tenure_months INT NOT NULL,
    source VARCHAR(255) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company_profile(id) ON DELETE CASCADE
);



ALTER TABLE public.credit_requirement ADD source_channel varchar(16) NULL;
ALTER TABLE public.credit_requirement ADD status varchar(16) NULL;


ALTER TABLE proposals ADD COLUMN credit_requirement_id BIGINT;
ALTER TABLE proposals ADD CONSTRAINT fk_proposal_credit_requirement FOREIGN KEY (credit_requirement_id) REFERENCES credit_requirement(id);


ALTER TABLE public.credit_requirement ADD source_channel varchar(16) NULL;
ALTER TABLE public.credit_requirement ADD status varchar(16) NULL;

ALTER TABLE public.credit_requirement RENAME COLUMN loa_format TO loan_format;


CREATE TABLE proposals (
    id bigserial PRIMARY KEY,
    lending_institution VARCHAR(255) NOT NULL,
    loan_duration INT NOT NULL,
    loan_amount BIGINT NOT NULL,
    interest_rate DECIMAL(10,2) NOT NULL,
    source_channel VARCHAR(255),
    proposal_date DATE NOT NULL,
    status VARCHAR(100)
);



ALTER TABLE proposals ADD COLUMN credit_requirement_id BIGINT;
ALTER TABLE proposals ADD CONSTRAINT fk_proposal_credit_requirement FOREIGN KEY (credit_requirement_id) REFERENCES credit_requirement(id);


create table if not exists credit_application (
id bigserial primary key,
company_id BIGINT NOT NULL,
    purpose VARCHAR(64),
    leadtime_at_discovery varchar(32),
    lead_age varchar(32),
    identified_on varchar(64),
    source_channel varchar(32),
    status varchar(16),
    insert_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES public.company_profile(id) ON DELETE CASCADE
);

create table if not exists applicant_details (
    id bigserial primary key ,
    credit_application_id bigint not null,
    pan VARCHAR(10) unique not null,
    full_name varchar(64),
    email varchar(128),
    phone varchar(16),
    employment_type varchar(16),
    income_per_annum int4,
    state varchar(16),
    pan_document varchar(1024),
    insert_timestamp TIMESTAMP default CURRENT_TIMESTAMP,
    foreign key (credit_application_id) references public.credit_application(id) on delete cascade
);

create table if not exists asset_liabilities (
id bigserial primary key ,
    credit_application_id bigint not null,
    promoter_name varchar(128),
    statutory_id varchar(128),
    tax_id varchar(128),
    dob date,
    mobile varchar(16),
    designation varchar(32),
    doj date,
    "source" varchar(64),
    foreign key (credit_application_id) references public.credit_application(id) on delete cascade
);





create table if not exists collateral_security (
id bigserial primary key ,
    credit_application_id bigint not null,
    bank_name varchar(128),
    account_holder varchar(128),
    account varchar(32),
    ifsc varchar(16),
    account_type varchar(16),
    currency varchar(16),
    opening_date date,
    "source" varchar(64),
    foreign key (credit_application_id) references public.credit_application(id) on delete cascade
);


create table if not exists account_assessment (
id bigserial primary key ,
    credit_application_id bigint not null,
    financial_year varchar(16),
    reported_revenue int4,
    gross_income int4,
    net_income int4,
    taxable_income int4,
    taxable_paid int4,
    "source" varchar(64),
    foreign key (credit_application_id) references public.credit_application(id) on delete cascade
);


create table if not exists financial_assessment (
id bigserial primary key ,
    credit_application_id bigint not null,
    financial_year varchar(16),
    "month" varchar(16),
    reported_sales int4,
    reported_purchases int4,
    taxes_paid int4,
    "source" varchar(64),
    foreign key (credit_application_id) references public.credit_application(id) on delete cascade
);


CREATE TABLE IF NOT EXISTS sanctioned_loans (
    loan_id BIGSERIAL PRIMARY KEY,
    credit_application_id BIGINT UNIQUE NOT NULL,
    sanctioned_amount BIGINT NOT NULL,
    disbursed_amount BIGINT NOT NULL,
    roi DECIMAL(5,2) NOT NULL,
    product VARCHAR(64) NOT NULL,
    tenure INT NOT NULL,
    emi BIGINT NOT NULL,
    overdue_amount BIGINT NOT NULL,
    status VARCHAR(32) NOT NULL,
    number_outstanding_request INT DEFAULT 0,
    amount_outstanding_request BIGINT DEFAULT 0,
    days_outstanding_request INT DEFAULT 0,
    FOREIGN KEY (credit_application_id) REFERENCES credit_application(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS disbursement_details (
    sr_no BIGSERIAL PRIMARY KEY,
    loan_id BIGINT NOT NULL,
    requested_date DATE NOT NULL,
    request_amount BIGINT NOT NULL,
    due_date DATE NOT NULL,
    requested_by VARCHAR(128) NOT NULL,
    designation VARCHAR(64),
    document VARCHAR(1024),
    status VARCHAR(32) NOT NULL,
    FOREIGN KEY (loan_id) REFERENCES sanctioned_loans(loan_id) ON DELETE CASCADE
);

CREATE TABLE statement_download (
    id SERIAL PRIMARY KEY,
    date_available DATE NOT NULL,
    document VARCHAR(255) NOT NULL,
    description TEXT,
    owner VARCHAR(255) NOT NULL,
    sanctioned_loan_id BIGINT NOT NULL,
    CONSTRAINT fk_sanctioned_loan FOREIGN KEY (sanctioned_loan_id) REFERENCES sanctioned_loans(loan_id) ON DELETE CASCADE
);


create table interest_rate_history (
    id SERIAL PRIMARY KEY,
account_id  BIGINT,
sanctioned_loan_id  BIGINT NOT null,
roi_change_date date,
roi_change_value DECIMAL(10,2),
outstanding_principle varchar(32),
roi_change_emi int4,
roi_change_tenure varchar(16),
    CONSTRAINT fk_sanctioned_loan FOREIGN KEY (sanctioned_loan_id) REFERENCES sanctioned_loans(loan_id) ON DELETE CASCADE
 );

create table registered_payment_method (
    id SERIAL PRIMARY KEY,
account_id BIGINT,
sanctioned_loan_id BIGINT NOT null,
bank_acc_no varchar(64),
bank_acc_type varchar(64),
bank_ifsc varchar(16),
bank_holder varchar(64),
payment_mode varchar(16),
status  varchar(16),
    CONSTRAINT fk_sanctioned_loan FOREIGN KEY (sanctioned_loan_id) REFERENCES sanctioned_loans(loan_id) ON DELETE CASCADE
 );



CREATE TABLE public.requirement_applicant_details (
    id BIGSERIAL PRIMARY KEY,
    credit_requirement_id BIGINT NOT NULL,
    pan VARCHAR(10) NOT NULL,
    full_name VARCHAR(64) NULL,
    applicant_name VARCHAR(64) NULL,
    applicant_type VARCHAR(32) NULL,
    gstin VARCHAR(15) NULL,
    gst_type VARCHAR(32) NULL,
    sector VARCHAR(32) NULL,
    industry VARCHAR(32) NULL,
    dob DATE NULL,
    pan_number VARCHAR(10) NULL,
    aadhar_card_number VARCHAR(16) null,
    msme_registration_number VARCHAR(32) NULL,
    pan_card VARCHAR(1024) NULL,
    aadhar_card VARCHAR(1024) NULL,
    msme_registration VARCHAR(1024) NULL,
    email VARCHAR(128) NULL,
    phone VARCHAR(16) NULL,
    income_per_annum INT4 NULL,
    state VARCHAR(16) NULL,
    insert_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_credit_requirement_applicant FOREIGN KEY (credit_requirement_id) REFERENCES credit_requirement(id)
);

CREATE TABLE public.applicant_location (
    id BIGSERIAL PRIMARY KEY,
    applicant_id BIGINT NOT NULL,
    address TEXT NOT NULL,
    city VARCHAR(64) NOT NULL,
    state VARCHAR(64) NOT NULL,
    zipcode VARCHAR(16) NOT NULL,
    country VARCHAR(64) NOT NULL,
    CONSTRAINT fk_applicant_location FOREIGN KEY (applicant_id) REFERENCES requirement_applicant_details(id) ON DELETE CASCADE
);

CREATE TABLE public.requirement_credit_context (
    id BIGSERIAL PRIMARY KEY,
    credit_requirement_id BIGINT NOT NULL,
    credit_score INT4 NOT NULL,
    total_loan_amount BIGINT NOT NULL,
    total_monthly_emi BIGINT NOT NULL,
    primary_bank_account_id BIGINT NULL,
    cancelled_check VARCHAR(1024) NULL,
    bank_account_statement VARCHAR(1024) NULL,
    insert_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_credit_requirement_context FOREIGN KEY (credit_requirement_id) REFERENCES credit_requirement(id),
    CONSTRAINT fk_primary_bank_account FOREIGN KEY (primary_bank_account_id) REFERENCES bank_account(id)
);

CREATE TABLE public.income_revenue_details (
    id BIGSERIAL PRIMARY KEY,
    credit_context_id BIGINT NOT NULL,
    financial_year INT NOT NULL,
    revenue_amount BIGINT NOT NULL,
    insert_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_credit_context FOREIGN KEY (credit_context_id) REFERENCES requirement_credit_context(id) ON DELETE CASCADE
);


CREATE TABLE public.bank_account (
    id BIGSERIAL PRIMARY KEY,
    credit_context_id BIGINT NOT NULL,
    account_number VARCHAR(32) NOT NULL,
    bank_name VARCHAR(64) NOT NULL,
    ifsc_code VARCHAR(16) NOT NULL,
    account_holder_name VARCHAR(64) NOT NULL,
    account_type VARCHAR(16) NOT NULL,
    insert_timestamp TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);






















