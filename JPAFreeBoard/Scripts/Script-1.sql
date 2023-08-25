SELECT * FROM EMPLOYEES e ;

SELECT 
 FIRST_NAME || '' || LAST_NAME "이름"
 FROM EMPLOYEES e 
 ORDER BY
 EMPLOYEE_ID DESC;
 
-- 앞에 줄번호를 붙여보자
SELECT 
rownum, R.*
FROM
(
SELECT
	FIRST_NAME ||''|| LAST_NAME "이름"
FROM 
	EMPLOYEES e 
ORDER BY 
	EMPLOYEE_ID DESC
)R;


-- 여기에 조건을 주면 상위 몇개만 조히가 가능하다
SELECT * FROM EMPLOYEES e;

-- 이름만 출력
SELECT 
   FIRST_NAME || ' ' || LAST_NAME  "이름"
FROM EMPLOYEES e 
   ORDER BY EMPLOYEE_ID DESC;
   
-- 앞에 줄번호를 붙여보자
SELECT 
   rownum, R.*
FROM 
   (
   SELECT 
      FIRST_NAME || ' ' || LAST_NAME  "이름"
   FROM EMPLOYEES e 
   ORDER BY EMPLOYEE_ID DESC
   ) R;
   
-- 여기에 조건을 주면 상위 몇개만 조회가 가능하다.
SELECT 
   rownum, R.*
FROM 
   (
   SELECT 
      FIRST_NAME || ' ' || LAST_NAME  "이름"
   FROM EMPLOYEES e 
   ORDER BY EMPLOYEE_ID DESC
   ) R
WHERE rownum<=10; -- rownum으로 조건을 준다.

-- 위의 코드를 쓰면 뒷부분을 날릴 수 있다.
-- 여기에 다시 한번 쿼리를 쓰면 앞부분을 날릴 수 있다.
-- 원하는 범위의 행만 조회가 가능하다.

SELECT
   Q.*
FROM 
   (
   SELECT 
      rownum rnum, R.*
   FROM 
      (
         SELECT 
            FIRST_NAME || ' ' || LAST_NAME  "이름"
         FROM EMPLOYEES e 
         ORDER BY EMPLOYEE_ID DESC
         ) R
      WHERE rownum<=10 -- 몇번까지
   ) Q
WHERE rnum>=5; -- 몇번부터 


		
	