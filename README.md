# CS23 - computer science

### STEP 1 : 디지털 논리회로
- NAND 게이트
- NOR 게이트
- 이진 덧셈기
- 진법 변환기

### STEP 2 : 컴퓨터 시뮬레이터
- MEMORY 만들기
- CPU 만들기
- 시뮬레이션하기

### STEP 3 : VMGit
-  DVCS 분산 저장소 방식에 대해 학습하고, 기본 동작을 구현
- init : 저장소를 init 하고 checkout 으로 이동하는 과정을 구현
- add, commit : 파일을 생성하고, 추가하고, 커밋하는 흐름을 작성
- push : 로컬에 커밋한 내용을 remote에 반영하는 흐름을 구현
- clone : remote에 있는 저장소를 local에 가져오는 방식을 구현

### STEP 4 : 빌드 자동화 스크립트
- 빌드 자동화 스크립트 구현
  - 셀 스크립트로 스탭1,2,3에서 만든 코드들을 빌드하는 과정을 자동화해서 구현
  - 스크립트를 실행하면, STEP1부터 STEP3까지 선택, 스탭을 입력하면 해당 스탭 소스만 빌드
  - 반드시 빌드 진행과정을 파일명과 함께 화면에 표시
  - 에러가 발생할 경우에는 에러에 대한 출력도 표시
  - 빌드한 실행 파일을 미리 지정한 위치로 복사하도록 지정한다. 권한이 없으면 에러를 출력한다.
  - 이전 실행 파일은 파일이름에 날짜를 붙여서 백업한다.

