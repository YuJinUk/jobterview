<template>
    <div class="modal-background">
        <div class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!-- <div class="modal-header">
                        
                    </div> -->
                    <div class="modal-body">
                        <h5 class="text-center text-danger"><i class="bi bi-exclamation-circle-fill"></i>  신고하기</h5>
                        <p class="mt-3">신고할 사용자 : {{ reportedNickname }}</p>
                        <textarea class="form-control" rows="5" v-model="reason" placeholder="신고 사유 입력..."></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-danger" @click="sendReport">제출</button>
                        <button type="button" class="btn btn-outline-secondary" @click="closeModal">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import { postReportAPI } from '@/api/reportApi';

export default {
    name: "ReportModal",
    props: {
        reportedNickname: String,
        reporterNickname: String,
    },
    data() {
        return {
            reason: "",
        }
    },
    methods: {
        closeModal() {
            this.$emit('close-modal-event');
        },
        saveChanges() {
            this.closeModal();
        },
        // Dto에 맞춰서 백엔드로 전송할 객체 생성
        async sendReport() {
            if(this.reason.length > 250 || this.reason.length <= 0) {
                alert("신고 사유는 1자 이상 250자 이하여야 합니다.");
                return;
            }
            let report = {
                reporterNickname: this.reporterNickname,
                reportedNickname: this.reportedNickname,
                reason: this.reason,
            }
            await postReportAPI(report, () => {
                alert("신고가 접수되었습니다.");
            }, 
            (error) => {
                console.log(error);
            });
            this.closeModal();
        }
    }
}
</script>
  
<style scoped>
/* Override default value of 'none' */
.modal {
    display: block;
    z-index: 99;
}

.modal-background {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.8);
    z-index: 100;
}</style>