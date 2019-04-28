#!/bin/bash

#스크립트를 실행하면, STEP1부터 STEP3까지 선택할 수 있다. 스탭을 입력하면 해당 스탭 소스만 빌드한다.
#반드시 빌드 진행과정을 파일명과 함께 화면에 표시한다.
#에러가 발생할 경우에는 에러에 대한 출력도 표시한다.
#빌드한 실행 파일을 미리 지정한 위치로 복사하도록 지정한다. 권한이 없으면 에러를 출력한다.
#이전 실행 파일은 파일이름에 날짜를 붙여서 백업한다.

echo "\n\n\n"

echo "====================================================================================================="
echo "##################################### MY FIRST SHELL SCRIPT  #########################################"
echo "______           _________ _        ________     _______  _______    _______ _________ _______  _______ 
(  ___ \ |\     /|\__   __/( \      (  __  \      (  ____ \(  ____ \    (  ____ \\__   __/(  ____ \(  ____ )
| (   ) )| )   ( |   ) (   | (      | (  \  )    | (    \/| (    \/     | (    \/   ) (   | (    \/| (    )|
| (__/ / | |   | |   | |   | |      | |   ) |    | |      | (_____      | (_____    | |   | (__    | (____)|
|  __ (  | |   | |   | |   | |      | |   | |    | |      (_____  )     (_____  )   | |   |  __)   |  _____)
| (  \ \ | |   | |   | |   | |      | |   ) |    | |            ) |           ) |   | |   | (      | (      
| )___) )| (___) |___) (___| (____/\| (__/  )    | (____/\/\____) |     /\____) |   | |   | (____/\| )      
|/ \___/ (_______)\_______/(_______/(______/     (_______/\_______)     \_______)   )_(   (_______/|/       

"

####### config #######

# rootDir : 빌드할 소스가 들어있는 디렉토리
# targetDir : 빌드한 실행 파일이  위치할 목표 디렉토리
# scriptLocation : 스크립트 파일 위치 

rootDir="/home/dongwon/shellPractice/CS23-Computer-Science"
targetDir="/home/dongwon/shellPractice/target/"
scriptLocation="/home/dongwon/shellPractice/autoBuildScript.sh"


# 현재 root dir 구조

:<<'END'
├── CS23-Computer-Science
│   ├── README.md
│   ├── step1
│   │   ├── Adder.java
│   │   ├── AdderTest.java
│   │   ├── Converter.java
│   │   └── ConverterTest.java
│   ├── step2
│   │   ├── ALU.java
│   │   ├── CPU.java
│   │   ├── CPUTest.java
│   │   ├── Instruction.java
│   │   ├── Memory.java
│   │   ├── MemoryTest.java
│   │   ├── Register.java
│   │   └── SimulationTest.java
│   └── step3
│       ├── GitArea.java
│       ├── Instruction.java
│       ├── InstructionUtils.java
│       ├── RemoteArea.java
│       ├── VmGitProgram.java
│       └── VmGitProgramUtils.java

END


####### utils ########

# progress Bar 표시
createProgressBar() 
{

	echo -ne '#####                     (33%)\r'
        sleep 0.5
	echo -ne '#############             (66%)\r'
	sleep 0.5
	echo -ne '#######################   (100%)\r'
	echo -ne '\n'

	return	
}

# 현재 시간 구하기
timestamp() {
  date +"%y-%m-%d-%T"
}

# 파일 백업
backup() {

	currentTime=$(timestamp)
	backupPath="/home/dongwon/backup/$currentTime"
	mkdir -p "$backupPath"
	cp -r "$targetDir/" "$backupPath"
}



echo -n  "현재시각 : "
timestamp

# get source dir

dirCount=1
STEPS=()
find $rootDir -type d | grep -v ".git" | while read dir
do
	if [[ $dir != $rootDir ]]; then
		STEPS[dirCount]=$dir
	fi
	dirCount+=1	
done

# get step name

stepCount=1
OPTIONS=()
for STEP in ${STEPS}
do
	stepName=${STEP:${#STEP}-5}
	OPTIONS[stepCount]=$stepName
	stepCount+=1
done


echo "WELCOME TO MY FIRST SHELL SCRIPT"
echo "SELECT THE STEP TO BUILD IN THESE OPTIONS \n"


# create step option

for option in ${OPTIONS[@]}
do
echo "[ ${option} ] \n"
done

echo -n ">"
read input


isExistStepName=0
for STEP in ${STEPS[@]}
do
	stepName=${STEP:${#STEP}-5}

	if [[ $input == $stepName ]]; then
		isExistStepName=1
		echo "${stepName}을 선택하셨습니다"
 		echo "다음 파일을 빌드합니다"	

		find $STEP | grep ".java" | grep -v "Test" | while read file
			do
				if [ -x $file ]; then
					echo "permission is granted!"
					echo $file

					backup

					javac -d "$targetDir" $file
					createProgressBar
				else
					echo "permission is denied!"
				       	echo "$file 실행 권한이 없습니다"
				fi
			done
	fi 	
done


if [ $isExistStepName -eq 0 ]; then
	echo "not valid command, try again"
	source $scriptLocation
fi
