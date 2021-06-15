import { ActivityDetail } from './activity-detail.model';
import { ActivitySummary } from './activity-summary.model';

export interface Activity {
    activityDetail: ActivityDetail;
    activitySummary: ActivitySummary;
}