CREATE TABLE LOT (
  Lot_ID INT,
  Pricing_Scheme_Number INT NOT NULL,
  Lot_Name VARCHAR(255),
  Lot_Address VARCHAR(255),
  Lot_Capacity INT NOT NULL,
  CONSTRAINT PK_LOT PRIMARY KEY (Lot_ID)
);