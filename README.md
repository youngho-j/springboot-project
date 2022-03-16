# 스프링 부트로 만드는 웹서비스 Clone (with AWS)

- 이동욱의 `스프링 부트와 AWS로 혼자 구현하는 웹 서비스` 구현의 결과물

  Spring boot, AWS를 활용하여 웹서비스 만들고 배포해보는 프로젝트입니다.

## 환경

- Java 8
- Gradle 4.x
- Spring Boot 2.1.x

## 오류 해결

<details>
<summary>코드 실행시 한글 깨짐 현상 해결</summary>

#### IDE, 프로젝트 인코딩 형식 변경 
1. 최상단 네비게이션 바 File 클릭 
2. 드롭다운 메뉴 중 Settings 클릭
3. 좌측 메뉴 중 Editor 클릭
4. File Encodings 탭 클릭
5. Global Encoding, Project Encoding, Properties Files 설정 UTF-8로 변경
6. 저장

#### Tomcat Server 인코딩 설정
1. 최상단 네비게이션 바 Run 클릭
2. Edit Configurations 클릭  
   [3, 4번 둘중에 원하는 방식으로 사용]  
   
3. Templates 클릭 후 Tomcat Server의 Local 클릭  
   [참고! 3 -> 4번 순서로 진행시 기본 세팅 값을 불러오지 않아 수동 세팅 필요.. 왜 그런지는 모르겠음..]  
4. 설정 창 좌측 상단 + 버튼(add new configuration, 단축키 alt + insert) 클릭 후 드롭다운 메뉴 중 Tomcat Server의 Local 클릭  
5. 정보 입력 후 VM options 항목에 -Dfile.encoding=UTF-8 추가 후 저장
        
#### JVM option 인코딩 설정
1. 최상단 네비게이션 바 Help 클릭
2. Edit Custom VM Options 클릭
3. idea64.exe.vmoptions 파일 실행
4. 맨 아랫줄 -Dfile.encoding=UTF-8 추가
5. 저장
        	
#### 참고 
`idea64.exe.vmoptions?`  
Windows의 실행 파일에 따라 IntelliJ IDEA의 JVM 옵션을 설정하는 파일  

#### 주의사항
JVM option 인코딩 설정에서 UTF-8 인코딩 설정시 꼭 Tomcat 인코딩 설정도 바꿔줘야함  
IDEA 콘솔과 Tomcat 인코딩 환경이 달라져 한글 깨짐 현상 발생
</details>
<details>
<summary>lombok 적용 후 테스트 메서드 실행시 오류 해결</summary>

#### 원인
 - 기존 설정된 gradle은 6.x 버전, 예제로 사용된 gradle 버전은 4.x 버전
 - lombok 설정이 gradle 5로 업그레이드 되면서 많은 변경점이 발생.. 6.x버전이면..

#### gradle 버전 확인
1. project 탭 gradle/wrapper/gradle-wrapper.properties 클릭
2. distributionUrl=https\:.../gradle-x.xx.x-bin.zip 에서 버전 확인

#### gradle 버전 변경(다운그레이드)
1. IntelliJ 터미널 창 [단축키 : `alt + F12`] (윈도우/맥 동일)
2. `gradlew wrapper --gradle-version 4.10.2` 명령어 입력하여 변경

#### 참고 
 - 터미널에서 gradle 버전 확인 방법 : `gradlew -v`  
   [명령어 입력시 리눅스 계열인 경우 명령어 앞에 ./ 추가]  
 - [참고링크](https://github.com/jojoldu/freelec-springboot2-webservice/issues/2)  
</details>
<details>
<summary>CodeDeploy 연동시 gradlew permission denied</summary>

#### 원인
 - gradlew 파일의 권한 문제로 인한 오류발생

#### 리눅스 권한 부여 명령어
 - chmod : 파일의 권한을 변경할 수 있게 만들어주는 명령어  
    `ch`(ange) + `mod`(e)의 앞글자를 조합   
    mode : comod 명령에서 지정하는 읽기(Read), 쓰기(Write), 실행(excute) 권한 및 소유자(User), 그룹(Group), 그 외 사용자(others)에 대한 설정값  

#### chmod 옵션
 - Ex) chmod [OPTION] [MODE] [FILE]
  ```
  OPTION
   -v : 모든 파일에 대해 모드가 적용되는 진단(diagnostic) 메시지 출력.
   -f : 에러 메시지 출력하지 않음.
   -c : 기존 파일 모드가 변경되는 경우만 진단(diagnostic) 메시지 출력.
   -R : 지정한 모드를 파일과 디렉토리에 대해 재귀적으로(recursively) 적용.
  MODE
   파일에 적용할 모드(mode) 문자열 조합.
   u,g,o,a : 소유자(u), 그룹(g), 그 외 사용자(o), 모든 사용자(a) 지정.
   +,-,= : 현재 모드에 권한 추가(+), 현재 모드에서 권한 제거(-), 현재 모드로 권한 지정(=)
   r,w,x  : 읽기 권한(r), 쓰기 권한(w), 실행 권한(x)  
     r : 디렉토리의 내용을 읽기 위해서는 읽기(r) 권한 필요.  
     w : 디렉토리의 내용을 변경하기 위해서는 쓰기(w) 권한 필요.  
     x : 디렉토리의 접근을 위해 실행(x) 권한 필요하며 가장 기본적인 접근 권한.  
   X : "디렉토리" 또는 "실행 권한(x)이 있는 파일"에 실행 권한(x) 적용.
   s : 실행 시 사용자 또는 그룹 ID 지정(s). "setuid", "setgid".
   t : 공유모드에서의 제한된 삭제 플래그를 나타내는 sticky(t) bit.
   0~7 : 8진수(octet) 형식 모드 설정 값.
  ```
#### chmod 사용예시
 - Ex) 파일을 `소유한 사용자`에 대해 `읽고 쓸 수` 있는 권한 `지정`  
    chmod u=rw [FILE]  

#### 참고 
 - 권한 지정은 전달된 권한으로 온전히 설정, 추가와 제거의 경우 기존 권한을 기준으로 권한이 추가 혹은 제거됨
 - 파일 또는 디렉토리에 지정된 권한 확인 명령어  
    `ls -l`  
 - [오류해결 참고링크](https://github.com/jojoldu/freelec-springboot2-webservice/issues/358)  
 - [명령어 참고링크](https://recipes4dev.tistory.com/175)   
</details>
<summary>EC2 -> RDS 접근 설정시 Connection refused</summary>

#### 현상
 - EC2 deploy.sh 수정 후 `curl localhos:8080` 명령어 실행 시 Connection refused 발생

#### 원인
 - deploy.sh 수정시 `\` 기호가 인식되지 않아 발생 [색상이 변해야 인식된 것]  
   `properties, \` 콤마 다음 공백표시로 인해 발생 

#### nohup
 - nohup : 리눅스에서 프로세스를 실행한 터미널의 세션 연결이 끊어지더라도 지속적으로 동작 할 수 있게 해주는 명령어  
    ```
    터미널에서 세션 로그아웃 발생시 리눅스가 해당 터미널에서 실행한 프로세스들에게 
    HUP signal 전달하여 종료를 시키게됨.
    HUP signal을 프로세스가 무시하도록 하는 명령이기 때문에 nohup이라고 불림 
    ```
 - nohup 명령어는 표준 출력(standard output)을 nohup.out 파일로 재지향(redirection) 합니다. [표준 출력이 nohup.out 파일에 기록된다는 의미]

#### nohup 사용법
 - `nohup [프로세스] &`  
   [프로세스] 부분에 실행하고자 하는 프로그램이나 스크립트를 지정(단, 스크립트 파일 권한이 755 이상이여야함)  
 - 명령어 끝부분 `&`은 일반적으로 nohup 사용시 백그라운드 작업으로 실행이 많아 명시해주는 것
 - nohup.out 파일 생성하지 않을 경우 `nohup [프로세스] 1>/dev/null 2>&1 &` 를 사용하여 표준 출력과 표준에러 재지향  
 - `1>/dev/null` 표준 출력을 사용하지 않겠다는 의미의 명령어
 - `2>&1` 표준 에러를 표준 출력과 같게하는 명령어  
     
#### 참고 
 - 특수문자 `>,<,>>` 는 입출력의 방향을 바꾸는 특수문자  
   >,<(꺾쇠)는 표준 출력 파일을 바꾸는 특수문자  
   Ex) echo "hi" > aa(파일 이름) -> aa 파일의 내용이 hi로 변경됨  
   >>(이중꺾쇠)는 파일에 내용을 추가하는 특수문자  
   Ex) echo "hi" >> aa(파일 이름) _> aa 파일 기존내용에 hi가 추가됨  
 - `echo` : 텍스트나 문자열을 보여주는 명령어   
 - [참고링크](https://github.com/jojoldu/freelec-springboot2-webservice/issues/82)  
 - [nohup 참고링크](https://gracefulprograming.tistory.com/128)   
</details>

## Reference
- 스프링 부트와 AWS로 혼자구현하는 웹서비스(이동욱 저)  
- [코드 예제](https://github.com/jojoldu/freelec-springboot2-webservice)  
