package ae.company.banking.infrastructure.repositories;

import ae.company.banking.domain.user.entities.User;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserRepositoryImpl {

	private ReactiveMongoTemplate mongoTemplate;

	public Mono<MonetaryAmount> findAccountBalanceByUserIdAndAccountId(String userId, String accountId) {
		var matchUser = Aggregation.match( Criteria.where( "_id" ).is( new ObjectId( userId ) ) );
		var unwindAccounts = Aggregation.unwind( "accounts" );
		var matchAccount = Aggregation.match( Criteria.where( "accounts._id" ).is( new ObjectId( accountId ) ) );
		var projectBalance = Aggregation.project().and( "accounts.balance" ).as( "balance" );

		var aggregation = Aggregation.newAggregation( matchUser, unwindAccounts, matchAccount, projectBalance );

		return mongoTemplate.aggregate( aggregation, User.class, AccountBalance.class )
				.next()
				.map( AccountBalance::getBalance );
	}

	@Getter private static class AccountBalance {
		private MonetaryAmount balance;
	}
}
