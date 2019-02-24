
MERGE INTO employee e
    USING employee h
    ON (e.emp_Id = h.emp_Id)
  WHEN MATCHED THEN
    UPDATE SET e.emp_Id = h.emp_Id
  WHEN NOT MATCHED THEN
    INSERT (emp_id, phone, email, employee_Name, emp_Type, password, last_updated_by, last_updated_date, create_date)
    VALUES (100, 91, 'admin@gmail.com', 'Zahid_Khan', 'MAN', 'admin_admin', 'SYSTEM', TO_DATE('2019-02-23', 'YYYY-MM-DD'), TO_DATE('2019-02-23', 'YYYY-MM-DD'));