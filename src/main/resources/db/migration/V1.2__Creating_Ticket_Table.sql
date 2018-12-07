CREATE TABLE TICKET (
  Ticket_ID INT NOT NULL,
  Lot_ID INT NOT NULL,
  Ticket_Time_In DATETIME NOT NULL,
  Ticket_Time_Out DATETIME,
  Ticket_Price DOUBLE,
  Ticket_Lost BOOLEAN,

  CONSTRAINT PK_TICKET PRIMARY KEY (Ticket_ID),
  CONSTRAINT FK_LOT FOREIGN KEY (Lot_ID) REFERENCES LOT(Lot_ID)
);