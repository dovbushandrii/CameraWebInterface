#ifndef _AUTOFOCUS_H_
#define _AUTOFOCUS_H_

#include <map>
#include <string>

enum class FocusSettings {
	ONE_SHOT,
	AI_SERVO,
	AI_FOCUS,
	MANUAL,
	NOT_VALID = -1
};

std::map<std::string, FocusSettings> FocusName = {
	{"One Shot AF"	,FocusSettings::ONE_SHOT},
	{"AI Servo AF"	,FocusSettings::AI_SERVO},
	{"AI Focus AF"	,FocusSettings::AI_FOCUS},
	{"Manual Focus"	,FocusSettings::MANUAL},
	{"Not valid"	,FocusSettings::NOT_VALID}
};

std::string findFocusType(FocusSettings foc) {
	for (auto focus : FocusName) {
		if (focus.second == foc) {
			return focus.first;
		}
	}
	return "Unknown focus type";
}
#endif
