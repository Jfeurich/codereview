CREATE OR REPLACE TRIGGER <trigger_name>
BEFORE DELETE OR INSERT OR UPDATE
ON <table_name>
FOR EACH ROW
DECLARE
<value_declarations>
BEGIN
<trigger_code>;
EXCEPTION
<exception_code>;
END;