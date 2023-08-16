import AiQuestionView from "@/views/ai/AiQuestionView";
import AiWebCam from "@/views/ai/AiWebCam"
import AiWebCamtwo from "@/views/ai/AiWebCamtwo"
import AiWebCamthree from "@/views/ai/AiWebCamthree"
import AiWebCamfour from "@/views/ai/AiWebCamfour"
import AiResult from "@/views/ai/AiResult"
import AiPermission from "@/views/ai/AiPermission"

const aiRouter = [
  {
    path: "/ai/question",
    name: "AiQuestion",
    component: AiQuestionView,
  },
  {
    path: "/aiwebcam",
    name: "AiWebCam",
    component: AiWebCam,
  },
  {
    path: "/aiwebcamtwo",
    name: "AiWebCamtwo",
    component: AiWebCamtwo,
  },
  {
    path: "/aiwebcamthree",
    name: "AiWebCamthree",
    component: AiWebCamthree,
  },
  {
    path: "/aiwebcamfour",
    name: "AiWebCamfour",
    component: AiWebCamfour,
  },
  {
    path: "/airesult",
    name: "AiResult",
    component: AiResult,
  },
  {
    path: "/aipermission",
    name: "AiPermission",
    component: AiPermission,
  },
];

export default aiRouter;
