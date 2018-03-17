NOW := $(shell /bin/date "+%Y-%m-%d . %H:%M:%S")
AUTHOR_EMAIL := "jafeth.bendezu@avantica.net"
AUTHOR_NAME := "Jafeth"
PROJECT_NAME := Tianos-mobile

save:
	git add .
	git config --global user.email $(AUTHOR_EMAIL)
	git config --global user.name $(AUTHOR_NAME) --replace-all
	git commit -m "modifications made on: $(NOW)"
ifeq ($(http), 1)
	git push origin_http master
else
	git push origin master
endif

adb:
	export ANDROID_HOME=/home/jafeth/Android/Sdk
	export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
	sleep 2;
	adb kill-server
	adb start-server
