CREATE TABLE PRICING (
  Pricing_ID INT,
  Pricing_Scheme_Number INT NOT NULL,
  Pricing_Duration INT NOT NULL,
  Pricing_Granularity VARCHAR(1) NOT NULL,
  Pricing_Price DOUBLE,
  CONSTRAINT PK_PRICING PRIMARY KEY (Pricing_ID)
);