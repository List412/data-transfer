package Tables

import org.jetbrains.exposed.dao.IntIdTable

object JobsResume : IntIdTable() {
    val job = reference("job_id", Tables.Jobs).nullable()
    val resume = reference("resume_id", Tables.Resumes).nullable()
}