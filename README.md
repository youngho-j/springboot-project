# 스프링 부트로 만드는 웹서비스 Clone (with AWS)

- 이동욱의 `스프링 부트와 AWS로 혼자 구현하는 웹 서비스` 구현의 결과물

  Spring boot, AWS를 활용하여 웹서비스 만들고 배포해보는 프로젝트입니다.

## 환경설정

- Java 8
- Gradle 4.x
- Spring Boot 2.1.x

## 오류 해결

<details>
    <summary>Test 코드 실행시 한글 깨짐 현상 해결</summary>
        <div markdown="1">
        	IDE, 프로젝트 인코딩 형식 변경 
        	1. 최상단 네비게이션 바 File 클릭 
        	2. 드롭다운 메뉴 중 Settings 클릭
        	3. 좌측 메뉴 중 Editor 클릭
        	4. File Encodings 탭 클릭
        	5. Global Encoding, Project Encoding, Properties Files 설정 UTF-8로 변경
        	6. 저장

        	Tomcat Server 인코딩 설정
        	1. 최상단 네비게이션 바 Run 클릭
        	2. Edit Configurations 클릭
        	3. Templates 클릭
        	4. Tomcat Server의 Local 클릭
        	   [참고! 3 -> 4번 순서로 진행시 기본 세팅 값을 불러오지 않아 수동 세팅 필요.. 
        	   왜 그런지는 모르겠음..] 
        	3-1. 설정 창 좌측 상단 + 버튼(add new configuration, 단축키 alt + insert) 클릭
        	4-1. 드롭다운 메뉴 중 Tomcat Server의 Local 클릭
        	5-1. 정보 입력 후 VM options 항목에 -Dfile.encoding=UTF-8 추가 후 저장
        	
        	JVM option 인코딩 설정
        	1. 최상단 네비게이션 바 Help 클릭
        	2. Edit Custom VM Options 클릭
        	3. idea64.exe.vmoptions 파일 실행
        	4. 맨 아랫줄 -Dfile.encoding=UTF-8 추가
        	5. 저장
        	
        	참고 
        	idea64.exe.vmoptions?
        	Windows의 실행 파일에 따라 IntelliJ IDEA의 JVM 옵션을 설정하는 파일
        	
        	해당 옵션에서 UTF-8 인코딩 설정시 꼭 Tomcat 인코딩 설정도 바꿔줘야함
        	IDEA 콘솔과 Tomcat 인코딩 환경이 달라져 한글 깨짐 현상 발생
        </div>
</details>



## Reference

- 스프링 부트와 AWS로 혼자구현하는 웹서비스(이동욱 저)  
- [코드 예제](https://github.com/jojoldu/freelec-springboot2-webservice)  