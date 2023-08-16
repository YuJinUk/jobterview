import { apiTest } from "./apiTest";

async function postReportAPI(report, success, fail) {
  await apiTest.post("/report", report).then(success).catch(fail);
}

export { postReportAPI };
