#ifndef _TEMPSTATUS_H_
#define _TEMPSTATUS_H_

enum class EdsTempStatus {
    NORMAL                          = 0x00000000,
    WARNING                         = 0x00000001,
    REDUCED_FRAME                   = 0x00000002,
    LV_PROHIBITED                   = 0x00000003,
    SHOOT_PROHIBITED                = 0x00000004,
    DEGRADED_IMG_QUALITY            = 0x00000005,
    NORMAL_MOV_REST                 = 0x00020000,
    WARNING_MOV_REST                = 0x00020001,
    REDUCED_FRAME_MOV_REST          = 0x00020002,
    LV_PROHIBITED_MOV_REST          = 0x00020003,
    SHOOT_PROHIBITED_MOV_REST       = 0x00020004,
    DEGRADED_IMG_QUALITY_MOV_REST   = 0x00020005
}

#endif // !_TEMPSTATUS_H_
